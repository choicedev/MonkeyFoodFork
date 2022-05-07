package com.choice.repository

import com.choice.core.util.IResult
import com.choice.model.Recipe
import com.choice.model.RecipeToken
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getAllRecipes(): Flow<IResult<List<Recipe>>>

    suspend fun setFavorite(recipe: Recipe)

    suspend fun searchRecipe(query: String): Flow<IResult<List<Recipe>>>

    suspend fun getRecipeById(id: Int): Recipe?

}