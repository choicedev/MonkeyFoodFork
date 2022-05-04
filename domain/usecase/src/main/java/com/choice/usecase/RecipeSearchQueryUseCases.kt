package com.choice.usecase

import com.choice.core.usecase.ResultUseCases
import com.choice.core.util.IResult
import com.choice.model.Recipe
import com.choice.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeSearchQueryUseCases constructor(
    private val repository: RecipeRepository
) : ResultUseCases<String, List<Recipe>> {
    override suspend fun invoke(input: String): Flow<IResult<List<Recipe>>> {
        return repository.searchRecipe(input)
    }
}