package com.choice.splash.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

    val visible = remember{
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true){
        delay(1000)
        visible.value = true
        delay(1000*3)
        visible.value = false
        navController.navigate(MonkeyScreen.Home.route)
    }

    MonkeyBackgroundUI {
        MonkeyColumn(
            verticalArrangement = Arrangement.Center
        ) {
            MonkeyTitle(visible.value)
        }
    }

}