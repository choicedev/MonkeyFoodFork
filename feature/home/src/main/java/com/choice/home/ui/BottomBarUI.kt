package com.choice.home.ui

import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.choice.compose.MonkeyBar
import com.choice.compose.MonkeyScaffold
import com.choice.home.ui.compose.MainBottomBar
import com.choice.home.ui.components.BottomBarNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun BottomSheetUI(mainController: NavController) {
    val navController = rememberAnimatedNavController()
    val scaffoldState = rememberScaffoldState()

    MonkeyScaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomBarNavigation(navController)
        },
    ) {
        MainBottomBar(scaffoldState, navController, mainController)
    }
}