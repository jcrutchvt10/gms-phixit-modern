package com.gmsphixit.app.utils

import android.os.Build

object OSUtils {
    fun getAndroidVersion(): Int = Build.VERSION.SDK_INT
    fun getBuildId(): String = Build.DISPLAY
    fun isAtLeastAndroid17(): Boolean = Build.VERSION.SDK_INT >= 36
}
