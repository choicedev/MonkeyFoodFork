package com.choice.usecase

import com.choice.core.usecase.IBaseUseCase
import com.choice.core.util.IResult
import com.choice.model.Recipe
import com.choice.model.RecipeRequest
import com.choice.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IRecipeGetUseCase @Inject constructor(
    private val recipeGetRepository: RecipeRepository
): IBaseUseCase<RecipeRequest, List<Recipe>>{
    override suspend fun invoke(input: RecipeRequest): Flow<IResult<List<Recipe>>> {
        return recipeGetRepository.searchRecipe(input)
    }
}