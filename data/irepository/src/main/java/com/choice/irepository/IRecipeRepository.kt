package com.choice.irepository

import com.choice.core.modules.Network
import com.choice.core.remote.performnetworkCall
import com.choice.core.util.IResult
import com.choice.core.util.RepositoryException
import com.choice.local.dao.RecipeDao
import com.choice.model.Recipe
import com.choice.remote.ApiFoodFork
import com.choice.local.mapping.toDomain
import com.choice.local.mapping.toEntity
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
            println("REPOSITORY")
            dao.getAll().collect{
                if(it.isNotEmpty()){
                    println("$it")
                    emit(IResult.OnSuccess(it.map{i -> i.toDomain()}))
                    return@collect
                }
            }
            println("Loading")

            emit(IResult.OnLoading(true, "looking for ingredients..."))

            performnetworkCall {
                println("CALL WEBSERVICE")
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


    override suspend fun setFavorite(recipe: Recipe) {
        dao.favorite(recipe.id ?: -1, !recipe.favorite)
    }

    override suspend fun searchRecipe(query: String): Flow<IResult<List<Recipe>>> {
        return flow {
            dao.searchRecipeList(query).apply {
                emit(IResult.OnSuccess(this.map { i -> i.toDomain() }))
            }
        }
    }

    override suspend fun getRecipeById(id: Int): Recipe? {
        return dao.getById(id)?.toDomain()
    }

}
