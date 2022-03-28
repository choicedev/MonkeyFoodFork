package com.choice.home.util

import com.choice.model.Recipe

data class HomeState(
    val isLoading: Boolean = false,
    val recipeList: List<Recipe> = emptyList()
)