package com.gmsphixit.app.presentation.navigation

import android.content.SharedPreferences
import javax.inject.Inject

class NavigationManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
    }

    fun getInitialDestination(): ScreenDestination {
        val completed = sharedPreferences.getBoolean(KEY_ONBOARDING_COMPLETED, false)
        return if (completed) ScreenDestination.PackagePicker else ScreenDestination.Onboarding
    }

    fun markOnboardingCompleted() {
        sharedPreferences.edit().putBoolean(KEY_ONBOARDING_COMPLETED, true).apply()
    }
}
