package com.choice.usecase

import com.choice.core.usecase.BaseUseCase
import com.choice.core.util.IResult
import com.choice.param.FavoriteParam
import com.choice.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeSetFavoriteUseCase @Inject constructor(
    private val recipeGetRepository: RecipeRepository
) : BaseUseCase<FavoriteParam, Unit> {
    override suspend fun invoke(input: FavoriteParam): Flow<IResult<Unit>> {
        return recipeGetRepository.setFavorite(input.id, input.favorite)
    }
}