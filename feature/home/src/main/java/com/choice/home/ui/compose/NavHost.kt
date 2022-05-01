package com.choice.home.ui.compose

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.choice.design.util.BottomNavItem
import com.choice.recipes.ui.RecipesUI
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@Composable
fun MainBottomBar(
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    mainController: NavController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = BottomNavItem.Recipes.route,
    ) {
        composable(
            BottomNavItem.Recipes.route
        ) {
            RecipesUI(navController, scaffoldState, mainController)
        }

        composable(route = BottomNavItem.Favorite.route) {
            Text("NOUEI")
        }
    }
}
