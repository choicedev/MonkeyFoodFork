package com.choice.favorites.utils

sealed class FavoriteEvent {
    data class OnSelectFavorite(val id: Int) : FavoriteEvent()
}