package com.choice.home.ui.compose

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.choice.design.util.BottomNavItem
import com.choice.favorites.ui.FavoriteUI
import com.choice.recipes.navigation.composableRecipes
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@Composable
fun BottomNavHost(
    navController: NavHostController,
    mainController: NavController,
    scaffoldState: ScaffoldState
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = BottomNavItem.Recipes.route,
    ) {
        composableRecipes(navController, mainController, scaffoldState)

        composable(route = BottomNavItem.Favorite.route) {
            FavoriteUI(scaffoldState, mainController)
        }
    }
}
