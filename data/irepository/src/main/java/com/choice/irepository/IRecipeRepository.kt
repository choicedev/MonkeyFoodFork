package com.choice.irepository

import com.choice.core.modules.Network
import com.choice.core.remote.performnetworkCall
import com.choice.core.util.IResult
import com.choice.core.util.RepositoryException
import com.choice.core.util.UseCasesException
import com.choice.local.dao.RecipeDao
import com.choice.local.mapping.toDomain
import com.choice.local.mapping.toEntity
import com.choice.model.FavoriteChange
import com.choice.model.Recipe
import com.choice.model.RecipeRequest
import com.choice.remote.ApiFoodFork
import com.choice.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class IRecipeRepository @Inject constructor(
    private val dao: RecipeDao,
    @Network.Food2Fork private val webservice: ApiFoodFork
) : RecipeRepository {

    override suspend fun searchRecipe(recipeRequest: RecipeRequest): Flow<IResult<List<Recipe>>> {
        return flow{

            dao.getAll()?.takeIf {
                it.isNotEmpty()
            }?.apply {
                emit(IResult.OnSuccess(this.map { i -> i.toDomain() }))
            } ?:
            performnetworkCall {
                webservice.search(page = recipeRequest.page, query = recipeRequest.query)
            }.catch {
                return@catch
            }.collect {
                when(it){
                    is IResult.OnSuccess -> {
                        it.response.results?.let { recipe ->
                            dao.insert(recipe.map { i -> i.toEntity() })
                            emit(IResult.OnSuccess(recipe))
                        } ?: emit(IResult.OnFailed(RepositoryException("empty list\n\n${it.response.results}")))
                    }

                    is IResult.OnFailed -> {
                        emit(IResult.OnFailed(it.throwable))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun setFavorite(favoriteChange: FavoriteChange): Flow<IResult<Unit>> {
        return flow{
            dao.favorite(favoriteChange.id, favoriteChange.favorite).also {
                if(it == 1) emit(IResult.OnSuccess(Unit)) else emit(IResult.OnFailed(RepositoryException("Desfavoritado")))
            }
        }
    }

}