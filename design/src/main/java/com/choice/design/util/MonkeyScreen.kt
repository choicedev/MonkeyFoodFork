package com.choice.design.util

sealed class MonkeyScreen(val route: String) {
    object Splash : MonkeyScreen("splash_screen")
    object Home   : MonkeyScreen("home_screen")
}