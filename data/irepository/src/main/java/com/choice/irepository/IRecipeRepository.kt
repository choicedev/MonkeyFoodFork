package com.choice.irepository

import com.choice.core.modules.Network
import com.choice.core.remote.performnetworkCall
import com.choice.core.util.IResult
import com.choice.core.util.RepositoryException
import com.choice.local.dao.RecipeDao
import com.choice.local.mapping.toDomain
import com.choice.local.mapping.toEntity
import com.choice.model.Recipe
import com.choice.model.RecipeToken
import com.choice.remote.ApiFoodFork
import com.choice.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class IRecipeRepository @Inject constructor(
    @Network.Food2Fork private val webservice: ApiFoodFork,
    private val dao: RecipeDao
) : RecipeRepository {

    override suspend fun getAllRecipes(): Flow<IResult<List<Recipe>>> {
        return flow {

            dao.getAll()?.takeIf {
                it.isNotEmpty()
            }?.apply {
                emit(IResult.OnSuccess(this.map { i -> i.toDomain() }))
                return@flow
            }

            emit(IResult.OnLoading(true, "looking for ingredients..."))

            performnetworkCall {
                webservice.search()
            }.catch {
                emit(IResult.OnFailed(it))
                return@catch
            }.collect {
                when (it) {
                    is IResult.OnSuccess -> {
                        it.response.results?.let { recipe ->
                            dao.insert(recipe.map { i -> i.toEntity() })
                            emit(IResult.OnSuccess(recipe))
                            emit(IResult.OnLoading(false))
                            return@collect
                        }
                            ?: emit(IResult.OnFailed(RepositoryException("list is null\n\n${it.response.results}")))
                    }

                    is IResult.OnFailed -> {
                        emit(IResult.OnFailed(it.throwable))
                    }
                }

            }
            emit(IResult.OnLoading(false))
        }.flowOn(Dispatchers.IO)
    }


    override suspend fun setFavorite(id: Int, favorite: Boolean): Flow<IResult<Unit>> {
        return flow {
            dao.favorite(id, favorite).also {
                if (it == 1) emit(IResult.OnSuccess(Unit)) else emit(
                    IResult.OnFailed(
                        RepositoryException("Desfavoritado")
                    )
                )
            }
        }
    }

    override suspend fun searchRecipe(query: String): Flow<IResult<List<Recipe>>> {
        return flow {
            dao.searchRecipeList(query).apply {
                emit(IResult.OnSuccess(this.map { i -> i.toDomain() }))
            }
        }
    }

    override suspend fun getRecipeById(id: Int): Flow<IResult<Recipe>> {
        return flow {
            dao.getById(id)?.apply {
                emit(IResult.OnSuccess(this.toDomain()))
            } ?: emit(IResult.OnFailed(RepositoryException("recipe not found")))
        }
    }

}
