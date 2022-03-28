package com.choice.monkeyfoodfork.ui.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.choice.design.util.MonkeyScreen
import com.choice.feature.navigation.navigation.composableFeatures
import com.choice.splash.navigation.composableSplash

@Composable
fun MonkeyHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MonkeyScreen.Splash.route
    ){
        composableSplash(navController)
        composableFeatures(navController)
    }
}