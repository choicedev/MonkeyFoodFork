package com.choice.feature.navigation.ui.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.choice.design.util.BottomNavItem
import com.choice.home.navigation.composableHomeBar
import com.choice.theme.MonkeyTheme

@Composable
fun MainBottomBar(
    scaffoldState: ScaffoldState,
    navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Recipes.route
    ){
        composableHomeBar(scaffoldState, navController)

        composable(route = BottomNavItem.Favorite.route){
            Text("NOUEI")
        }
    }
}
