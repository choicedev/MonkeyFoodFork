package com.choice.usecase

import com.choice.core.usecase.BaseUseCase
import com.choice.core.util.IResult
import com.choice.model.Recipe
import com.choice.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) : BaseUseCase<Unit, List<Recipe>> {
    override suspend fun invoke(input: Unit): Flow<IResult<List<Recipe>>> {
        return repository.getAllRecipes()
    }
}