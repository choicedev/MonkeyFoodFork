package com.choice.home.util

sealed class HomeEvent {
    data class Favorite(val id: Int, val favorite: Boolean) : HomeEvent()
}