package com.gmsphixit.app.presentation.navigation

sealed class ScreenDestination(val route: String) {
    data object Onboarding : ScreenDestination("onboarding")
    data object PackagePicker : ScreenDestination("package_picker")
    data object FlagsChanger : ScreenDestination("flags_changer/{packageName}/{appName}") {
        fun createRoute(packageName: String, appName: String): String =
            "flags_changer/$packageName/$appName"
    }
    data object Settings : ScreenDestination("settings")
}
