package com.choice.recipes.util

import com.choice.model.Recipe

sealed class RecipesEvent {
    data class OnFavoriteChange(val recipe: Recipe) : RecipesEvent()
    data class OnSearchQueryChange(val query: String) : RecipesEvent()
    object OnRestoreFavorite : RecipesEvent()
}