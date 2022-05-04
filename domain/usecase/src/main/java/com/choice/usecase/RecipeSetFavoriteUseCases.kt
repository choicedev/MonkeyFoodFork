package com.choice.usecase

import com.choice.core.usecase.UseCases
import com.choice.model.Recipe
import com.choice.repository.RecipeRepository

class RecipeSetFavoriteUseCases constructor(
    private val recipeGetRepository: RecipeRepository
) : UseCases<Recipe, Unit>{
     override suspend fun invoke(input: Recipe){
        recipeGetRepository.setFavorite(input)
    }
}