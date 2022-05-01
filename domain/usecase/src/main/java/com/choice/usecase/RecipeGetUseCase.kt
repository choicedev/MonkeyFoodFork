package com.choice.usecase

import com.choice.core.usecase.BaseUseCase
import com.choice.core.util.IResult
import com.choice.model.Recipe
import com.choice.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeGetUseCase @Inject constructor(
    private val repository: RecipeRepository
) : BaseUseCase<Int, Recipe> {
    override suspend fun invoke(input: Int): Flow<IResult<Recipe>> {
        return repository.getRecipeById(input)
    }
}