package com.choice.monkeyfoodfork.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.choice.compose.MonkeySurface
import com.choice.theme.MonkeyTheme
import com.choice.theme.md_theme_light_background
import com.choice.theme.md_theme_light_onPrimary
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppUI() {
    val systemUiController = rememberSystemUiController()

    MonkeySurface {
        MonkeyHost()
    }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = md_theme_light_background
        )

        systemUiController.setNavigationBarColor(
            color = md_theme_light_background
        )
    }
}