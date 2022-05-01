package com.choice.splash.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.choice.components.MonkeyBackgroundUI
import com.choice.compose.MonkeyColumn
import com.choice.design.util.MonkeyScreen
import com.choice.splash.ui.component.MonkeyTitle
import kotlinx.coroutines.delay

@Composable
fun SplashUI(
    navController: NavController
) {

    var visible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true){
        delay(1000)
        visible = !visible
        delay(1000*3)
        visible = !visible
        navController.navigate(MonkeyScreen.Main.route)
    }

    MonkeyBackgroundUI {
        MonkeyColumn(
            verticalArrangement = Arrangement.Center
        ) {
            MonkeyTitle(visible)
        }
    }

}