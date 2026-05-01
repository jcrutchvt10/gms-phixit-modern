package com.gmsphixit.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import com.gmsphixit.app.presentation.core.ui.theme.GMSPhixitTheme
import com.gmsphixit.app.presentation.navigation.NavigationManager
import com.gmsphixit.app.presentation.navigation.RootNavDisplay
import com.gmsphixit.app.presentation.navigation.ScreenDestination
import com.gmsphixit.app.core.phixit.InitRootDB
import com.gmsphixit.app.core.shell.InitShell
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootDBInitializer: InitRootDB

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val startDestination = navigationManager.getInitialDestination()
        if (startDestination == ScreenDestination.PackagePicker) {
            InitShell.initShell()
            rootDBInitializer.initDB()
        }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(),
                Color.Transparent.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(),
                Color.Transparent.toArgb()
            )
        )

        setContent {
            GMSPhixitTheme {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceContainerHighest
                ) {
                    RootNavDisplay(startDestination = startDestination)
                }
            }
        }
    }
}
