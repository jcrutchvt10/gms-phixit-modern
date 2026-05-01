package com.gmsphixit.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gmsphixit.app.presentation.feature.flagsChanger.FlagsChangerScreen
import com.gmsphixit.app.presentation.feature.flagsChanger.FlagsChangerViewModel
import com.gmsphixit.app.presentation.feature.onboarding.OnboardingScreen
import com.gmsphixit.app.presentation.feature.onboarding.OnboardingViewModel
import com.gmsphixit.app.presentation.feature.packagePicker.PackagePickerScreen
import com.gmsphixit.app.presentation.feature.packagePicker.PackagePickerViewModel
import com.gmsphixit.app.presentation.feature.settings.SettingsScreen
import com.gmsphixit.app.presentation.feature.settings.SettingsViewModel
import com.gmsphixit.app.presentation.navigation.anim.NavigationAnimation
import java.net.URLDecoder

@Composable
fun RootNavDisplay(startDestination: ScreenDestination) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        enterTransition = { NavigationAnimation.enterTransition },
        exitTransition = { NavigationAnimation.exitTransition },
        popEnterTransition = { NavigationAnimation.popEnterTransition },
        popExitTransition = { NavigationAnimation.popExitTransition }
    ) {
        composable(ScreenDestination.Onboarding.route) {
            val viewModel: OnboardingViewModel = hiltViewModel()
            OnboardingScreen(
                viewModel = viewModel,
                onRootConfirm = {
                    navController.navigate(ScreenDestination.PackagePicker.route) {
                        popUpTo(ScreenDestination.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(ScreenDestination.PackagePicker.route) {
            val viewModel: PackagePickerViewModel = hiltViewModel()
            PackagePickerScreen(
                viewModel = viewModel,
                onClick = { packageName, appName ->
                    navController.navigate(
                        ScreenDestination.FlagsChanger.createRoute(packageName, appName)
                    )
                },
                onSettingsClick = {
                    navController.navigate(ScreenDestination.Settings.route)
                }
            )
        }

        composable(
            route = ScreenDestination.FlagsChanger.route,
            arguments = listOf(
                navArgument("packageName") { type = NavType.StringType },
                navArgument("appName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val packageName = backStackEntry.arguments?.getString("packageName") ?: ""
            val appName = URLDecoder.decode(
                backStackEntry.arguments?.getString("appName") ?: "", "UTF-8"
            )
            val viewModel: FlagsChangerViewModel = hiltViewModel()
            FlagsChangerScreen(
                viewModel = viewModel,
                packageName = packageName,
                appName = appName,
                onBack = { navController.popBackStack() }
            )
        }

        composable(ScreenDestination.Settings.route) {
            val viewModel: SettingsViewModel = hiltViewModel()
            SettingsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
