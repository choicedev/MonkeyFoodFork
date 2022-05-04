package com.choice.home.ui

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.choice.compose.MonkeyScaffold
import com.choice.design.util.BottomNavItem
import com.choice.home.ui.compose.BottomNavHost
import com.choice.home.ui.components.BottomBarNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun MainUI(mainController: NavController) {
    val navController = rememberAnimatedNavController()
    val scaffoldState = rememberScaffoldState()

    MonkeyScaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomBarNavigation(navController)
        },
    ) {
        BottomNavHost(
            navController,
            mainController,
            scaffoldState
        )
    }
}