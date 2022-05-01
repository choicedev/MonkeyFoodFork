package com.choice.usecase

import com.choice.core.usecase.BaseUseCase
import com.choice.core.util.IResult
import com.choice.model.Recipe
import com.choice.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeSearchQueryUseCase @Inject constructor(
    private val repository: RecipeRepository
) : BaseUseCase<String, List<Recipe>> {
    override suspend fun invoke(input: String): Flow<IResult<List<Recipe>>> {
        return repository.searchRecipe(input)
    }
}