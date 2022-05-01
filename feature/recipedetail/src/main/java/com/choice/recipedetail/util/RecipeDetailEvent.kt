package com.choice.recipedetail.util

sealed class RecipeDetailEvent {
    data class OnFavoriteChange(val id: Int?, val favorite: Boolean) : RecipeDetailEvent()
}
