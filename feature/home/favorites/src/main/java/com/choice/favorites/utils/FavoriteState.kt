package com.choice.favorites.utils

import com.choice.model.Recipe

data class FavoriteState(
    val favoriteList: List<Recipe> = emptyList<Recipe>()
)