package com.choice.feature.navigation.ui

import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.choice.compose.MonkeyBar
import com.choice.compose.MonkeyScaffold
import com.choice.feature.navigation.ui.compose.MainBottomBar
import com.choice.home.ui.components.BottomBarNavigation

@Composable
fun FeaturesUI(mainController: NavController) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    MonkeyScaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomBarNavigation(navController)
        },
        snackbarHost = {
            SnackbarHost(it){ data ->
                MonkeyBar(data)
            }
        }
    ) {
        MainBottomBar(scaffoldState, navController)
    }
}