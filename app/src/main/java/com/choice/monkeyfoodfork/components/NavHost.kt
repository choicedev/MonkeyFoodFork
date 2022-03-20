package com.choice.monkeyfoodfork.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.choice.design.util.MonkeyScreen
import com.choice.splash.navigation.composableSplash
import com.choice.splash.ui.SplashUI
import com.google.accompanist.navigation.animation.AnimatedNavHost

@Composable
fun MonkeyHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MonkeyScreen.Splash.route
    ){
        composableSplash(navController)
    }
}