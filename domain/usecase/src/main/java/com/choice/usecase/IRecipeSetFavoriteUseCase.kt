package com.choice.usecase

import com.choice.core.usecase.IBaseUseCase
import com.choice.core.util.IResult
import com.choice.model.FavoriteChange
import com.choice.model.Recipe
import com.choice.model.RecipeRequest
import com.choice.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IRecipeSetFavoriteUseCase @Inject constructor(
    private val recipeGetRepository: RecipeRepository
): IBaseUseCase<FavoriteChange, Unit> {
    override suspend fun invoke(input: FavoriteChange): Flow<IResult<Unit>> {
        return recipeGetRepository.setFavorite(input)
    }
}