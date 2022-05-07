package com.choice.monkeyfoodfork.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import com.choice.design.util.MonkeyScreen
import com.choice.home.navigation.composableMain
import com.choice.recipedetail.navigation.composableRecipeDetail
import com.choice.splash.navigation.composableSplash
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun MonkeyHost() {
    val navController = rememberAnimatedNavController()
    val density = LocalDensity.current
    AnimatedNavHost(
        navController = navController,
        startDestination = MonkeyScreen.Splash.route,
    ){
        composableSplash(navController)
        composableMain(navController)
        composableRecipeDetail(navController, density)
    }
}