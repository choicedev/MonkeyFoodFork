package com.choice.repository

import com.choice.core.util.IResult
import com.choice.model.FavoriteChange
import com.choice.model.Recipe
import com.choice.model.RecipeRequest
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun searchRecipe(recipeRequest: RecipeRequest): Flow<IResult<List<Recipe>>>

    suspend fun setFavorite(favoriteChange: FavoriteChange): Flow<IResult<Unit>>

}