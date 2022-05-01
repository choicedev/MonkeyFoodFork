package com.choice.recipes.util

sealed class RecipesEvent {
    data class OnFavoriteChange(val id: Int?, val favorite: Boolean) : RecipesEvent()
    data class OnSearchQueryChange(val query: String) : RecipesEvent()
}