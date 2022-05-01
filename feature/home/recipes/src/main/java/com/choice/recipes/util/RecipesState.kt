package com.choice.recipes.util

import com.choice.model.Recipe

data class RecipesState(
    val isLoading: Boolean = false,
    val isLoadingText: String = "",
    val recipeList: List<Recipe> = emptyList(),
    val searchQuery: String = "",
    val error: Throwable? = null
)