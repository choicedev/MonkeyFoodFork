package com.choice.recipedetail.util

sealed class RecipeDetailEvent {
    object OnFavoriteChange : RecipeDetailEvent()
}
