package com.choice.home.navigation

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.choice.design.util.BottomNavItem
import com.choice.home.ui.HomeUI

fun NavGraphBuilder.composableHomeBar(scaffoldState: ScaffoldState, navController: NavController){
    composable(BottomNavItem.Recipes.route){
        HomeUI(navController, scaffoldState)
    }
}