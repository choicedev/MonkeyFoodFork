package com.choice.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.choice.design.util.MonkeyScreen
import com.choice.home.ui.BottomSheetUI
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.composableHome(navController: NavController) {
    composable(MonkeyScreen.Main.route) {
        BottomSheetUI(navController)
    }
}