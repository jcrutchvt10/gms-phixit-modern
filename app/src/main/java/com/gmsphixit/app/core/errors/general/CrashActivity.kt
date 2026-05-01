package com.gmsphixit.app.core.errors.general

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class CrashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val stackTrace = intent?.getStringExtra(Constants.EXTRA_EXCEPTION) ?: "No stack trace"
        setContent {
            GeneralCrashScreen(
                stackTrace = stackTrace,
                onRestart = {
                    val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
                    if (launchIntent != null) {
                        startActivity(launchIntent)
                    }
                    finish()
                },
                onExit = {
                    finishAffinity()
                }
            )
        }
    }
}
