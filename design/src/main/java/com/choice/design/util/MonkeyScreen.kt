package com.choice.design.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MonkeyScreen(
    val route: String
) {
    object Splash : MonkeyScreen("splash_screen")
    object Main   : MonkeyScreen("main_screen")
}