plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("kotlinx-serialization")
    id("com.google.protobuf")
}

android {
    namespace = "com.gmsphixit.app"
    compileSdk = 36
    buildToolsVersion = "35.0.1"

    defaultConfig {
        applicationId = "com.gmsphixit.app"
        minSdk = 29
        targetSdk = 36
        versionCode = 2
        versionName = "2.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        val signingKeyPath = System.getenv("SIGNING_KEY")
        val signingKeyStorePassword = System.getenv("KEYSTORE_PASSWORD")
        val signingKeyAlias = System.getenv("KEY_ALIAS")
        val signingKeyPassword = System.getenv("KEYSTORE_PASS")
        if (!signingKeyPath.isNullOrBlank() && !signingKeyStorePassword.isNullOrBlank()
            && !signingKeyAlias.isNullOrBlank() && !signingKeyPassword.isNullOrBlank()
        ) {
            create("release") {
                storeFile = File(signingKeyPath)
                storePassword = signingKeyStorePassword
                keyAlias = signingKeyAlias
                keyPassword = signingKeyPassword
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            if (signingConfigs.findByName("release") != null) {
                signingConfig = signingConfigs.getByName("release")
            }
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        compose = true
        aidl = true
    }

    sourceSets {
        getByName("main") {
            aidl.srcDirs("src/main/aidl")
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.3"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}

tasks.register("fixAidlHeader") {
    doLast {
        val aidlDir = file("build/generated/aidl_source_output_dir")
        if (!aidlDir.exists()) return@doLast
        aidlDir.walkTopDown().forEach { file ->
            if (file.extension == "java") {
                val content = file.readText()
                val pattern = Regex("/\\*.*?\\*/", RegexOption.DOT_MATCHES_ALL)
                val replacedContent = pattern.replace(content) { matchResult ->
                    matchResult.value.replace("\\", "/")
                }
                file.writeText(replacedContent)
            }
        }
    }
}

tasks.withType<com.android.build.gradle.tasks.AidlCompile>().configureEach {
    finalizedBy(tasks.named("fixAidlHeader"))
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.core)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.protobuf.javalite)

    implementation(libs.libsu.core)
    implementation(libs.libsu.service)
    implementation(libs.libsu.nio)
    implementation(libs.requery.sqlite)

    implementation(libs.bundles.coil)
    implementation(libs.ktor.client.okhttp)
}
