package com.choice.feature.navigation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.choice.design.util.MonkeyScreen
import com.choice.feature.navigation.ui.FeaturesUI

fun NavGraphBuilder.composableFeatures(navController: NavController){
    composable(MonkeyScreen.Main.route){
        FeaturesUI(navController)
    }
}