package com.choice.usecase

import com.choice.core.usecase.UseCases
import com.choice.model.Recipe
import com.choice.repository.RecipeRepository


class RecipeGetByIdUseCases constructor(
    private val repository: RecipeRepository
) : UseCases<Int, Recipe?> {
    override suspend fun invoke(input: Int): Recipe? {
        return repository.getRecipeById(input)
    }
}