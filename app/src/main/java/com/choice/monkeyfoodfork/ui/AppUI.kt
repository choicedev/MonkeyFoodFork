package com.choice.monkeyfoodfork.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.choice.compose.MonkeyScaffold
import com.choice.compose.MonkeySurface
import com.choice.home.ui.components.BottomBarNavigation
import com.choice.monkeyfoodfork.ui.compose.MonkeyHost
import com.choice.theme.md_theme_light_onPrimaryVariant
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppUI() {

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = md_theme_light_onPrimaryVariant
        )

        systemUiController.setNavigationBarColor(
            color = md_theme_light_onPrimaryVariant
        )
    }

    MonkeySurface {
        MonkeyHost()
    }

}