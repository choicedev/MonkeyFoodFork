package com.choice.design.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
){
    object Recipes : BottomNavItem("Recipes", Icons.Filled.MenuBook, "home_screen")
    object Favorite : BottomNavItem("Favorite", Icons.Filled.Favorite, "favorite_screem")
    object Info : BottomNavItem("Info", Icons.Filled.Info, "info_screen")

    companion object {
        val items = listOf(
            Recipes,
            Favorite,
            Info
        )
    }
}