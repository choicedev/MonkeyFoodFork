package com.choice.usecase

data class RecipeUseCases(
    val getRecipes: GetRecipesUseCases,
    val getRecipeById: RecipeGetByIdUseCases,
    val searchQuery: RecipeSearchQueryUseCases,
    val setFavorite: RecipeSetFavoriteUseCases
)