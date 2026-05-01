package com.gmsphixit.app.presentation.navigation.anim

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object NavigationAnimation {
    private const val ANIM_DURATION = 350

    val enterTransition: EnterTransition = slideInHorizontally(
        initialOffsetX = { it / 3 },
        animationSpec = tween(ANIM_DURATION)
    ) + fadeIn(animationSpec = tween(ANIM_DURATION))

    val exitTransition: ExitTransition = slideOutHorizontally(
        targetOffsetX = { -it / 3 },
        animationSpec = tween(ANIM_DURATION)
    ) + fadeOut(animationSpec = tween(ANIM_DURATION))

    val popEnterTransition: EnterTransition = slideInHorizontally(
        initialOffsetX = { -it / 3 },
        animationSpec = tween(ANIM_DURATION)
    ) + fadeIn(animationSpec = tween(ANIM_DURATION))

    val popExitTransition: ExitTransition = slideOutHorizontally(
        targetOffsetX = { it / 3 },
        animationSpec = tween(ANIM_DURATION)
    ) + fadeOut(animationSpec = tween(ANIM_DURATION))
}
