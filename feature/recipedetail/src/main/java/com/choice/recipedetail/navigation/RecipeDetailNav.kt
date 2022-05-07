package com.choice.recipedetail.navigation

import androidx.compose.animation.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.choice.design.util.MonkeyScreen
import com.choice.recipedetail.ui.RecipeDetailUI
import com.google.accompanist.navigation.animation.composable


fun NavGraphBuilder.composableRecipeDetail(navController: NavController, density: Density) {
    val enter = slideInVertically {
        with(density) { -100.dp.roundToPx() }
    } + expandVertically(
        expandFrom = Alignment.Top,
        clip = true
    ) + fadeIn(
        initialAlpha = 0f
    )
    composable(
        route = MonkeyScreen.RecipeDetail.route + "?recipeId={recipeId}",
        arguments = listOf(
            navArgument(
                name = "recipeId"
            ) {
                type = NavType.IntType
                defaultValue = -1
            }
        ),
        enterTransition = {
            enter
        },
        exitTransition = {
            fadeOut(targetAlpha = 0f)
        },
        popEnterTransition = {
            enter
        },
        popExitTransition = {
            fadeOut(targetAlpha = 0f)
        }
    ) {
        RecipeDetailUI(navController)
    }
}

