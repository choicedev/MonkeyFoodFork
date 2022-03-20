package com.choice.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.choice.design.util.MonkeyScreen
import com.choice.splash.ui.SplashUI

fun NavGraphBuilder.composableSplash(navController: NavController) {
    composable(MonkeyScreen.Splash.route){
        SplashUI(navController)
    }
}