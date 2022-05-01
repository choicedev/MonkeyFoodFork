package com.choice.recipedetail.ui.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.choice.components.MonkeyCenterTopAppBarBack
import com.choice.theme.MonkeyTheme

@Composable
fun RecipeTopAppBar(
    navController: NavController
) {
    MonkeyCenterTopAppBarBack(
        "",
        textColor = MonkeyTheme.colors.surface,
        backgroundColor = Color.Transparent,
        onBack = {
            navController.navigateUp()
        })
}

@Composable
fun RecipeTopAppBarAnimation(
    visible: Boolean,
    navController: NavController
) {
    AnimatedVisibility(visible = visible) {
        MonkeyCenterTopAppBarBack(
            "",
            backgroundColor = Color.Transparent,
            onBack = {
                navController.navigateUp()
            })
    }
}