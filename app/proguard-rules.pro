-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

-dontwarn com.google.protobuf.**
-keep class com.google.protobuf.** { *; }
-keep class io.requery.android.database.sqlite.** { *; }
-keep class com.topjohnwu.superuser.** { *; }
-keepclassmembers class * extends android.os.IInterface {
    public *** asInterface(android.os.IBinder);
}
-keepclassmembers class * {
    *** CREATOR;
}
