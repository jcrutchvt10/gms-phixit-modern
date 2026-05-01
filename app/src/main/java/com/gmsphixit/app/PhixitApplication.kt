package com.gmsphixit.app

import android.app.Application
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp
import com.gmsphixit.app.core.errors.general.CrashActivity
import com.gmsphixit.app.core.errors.general.ExceptionHandler

@HiltAndroidApp
class PhixitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (!BuildConfig.DEBUG) ExceptionHandler.initialize(this, CrashActivity::class.java)
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
