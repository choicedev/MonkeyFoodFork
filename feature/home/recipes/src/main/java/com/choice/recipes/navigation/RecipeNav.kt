package com.choice.recipes.navigation

import androidx.compose.material.ScaffoldState
import com.choice.design.util.BottomNavItem
import com.choice.recipes.ui.RecipesUI
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.choice.design.util.MonkeyScreen
import com.google.accompanist.navigation.animation.composable


fun NavGraphBuilder.composableRecipes(navController: NavController, mainNavController: NavController, scaffoldState: ScaffoldState) {
    composable(
        route = BottomNavItem.Recipes.route,
        enterTransition = {
            fadeIn(
                initialAlpha = 0.3f,
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            fadeOut(targetAlpha = 0f)
        },
        popEnterTransition = {
            fadeIn(initialAlpha = 0.3f)
        },
        popExitTransition = {
            fadeOut(targetAlpha = 0f)
        }
    ) {
        RecipesUI(navController, mainNavController, scaffoldState)
    }
}

