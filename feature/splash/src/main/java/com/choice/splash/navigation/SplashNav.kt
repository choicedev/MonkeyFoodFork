package com.choice.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.choice.design.util.MonkeyScreen
import com.choice.splash.ui.SplashUI
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.composableSplash(navController: NavController) {
    composable(MonkeyScreen.Splash.route){
        SplashUI(navController)
    }
}