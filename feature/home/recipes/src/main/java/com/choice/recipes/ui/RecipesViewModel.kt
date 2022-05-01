package com.choice.recipes.ui

import androidx.lifecycle.viewModelScope
import com.choice.core.util.IResult
import com.choice.core.viewModel.BaseViewModel
import com.choice.recipes.util.RecipesEvent
import com.choice.recipes.util.RecipesState
import com.choice.param.FavoriteParam
import com.choice.usecase.GetAllRecipesUseCase
import com.choice.usecase.RecipeSearchQueryUseCase
import com.choice.usecase.RecipeSetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getAllRecipesUseCases: GetAllRecipesUseCase,
    private val recipeFavoriteUseCase: RecipeSetFavoriteUseCase,
    private val recipeSearchQueryUseCase: RecipeSearchQueryUseCase
) : BaseViewModel<RecipesState, RecipesEvent>(RecipesState()) {

    override fun onEvent(event: RecipesEvent) {
        viewModelScope.launch {
            when (event) {
                is RecipesEvent.OnFavoriteChange -> {
                    onFavoriteChange(event.id, event.favorite)
                }

                is RecipesEvent.OnSearchQueryChange -> {
                    state = state.copy(searchQuery = event.query)
                    onSearchChange()
                }
            }
        }
    }

    private fun onFavoriteChange(
        id: Int?,
        favorite: Boolean
    ) = viewModelScope.launch {
        recipeFavoriteUseCase(
            FavoriteParam(id ?: -1, favorite)
        ).catch { showSnackbar("$it") }
            .collect {
                when (it) {
                    is IResult.OnSuccess -> {
                        if (state.searchQuery.isEmpty()) {
                            getRecipes()
                        } else {
                            onSearchChange()
                        }
                    }
                    is IResult.OnFailed -> {
                        showSnackbar("Failed\n${it.throwable.message}")
                    }
                }
            }
    }

    private fun onSearchChange(
        query: String = state.searchQuery.lowercase(),
    ) = viewModelScope.launch {
        recipeSearchQueryUseCase(query)
            .catch { showSnackbar("${it.message}") }
            .collect { result ->
                when (result) {
                    is IResult.OnSuccess -> {
                        state = state.copy(
                            recipeList = result.response
                        )
                    }

                    is IResult.OnLoading -> {
                        state = state.copy(
                            isLoading = result.isLoading,
                            isLoadingText = result.isLoadingText
                        )
                    }

                    is IResult.OnFailed -> {
                        showSnackbar("${result.throwable.message}")
                    }

                }
            }
    }

    suspend fun getRecipes() {
        getAllRecipesUseCases(Unit)
            .collect {
                when (it) {
                    is IResult.OnSuccess -> {
                        state = state.copy(
                            recipeList = it.response
                        )
                    }
                    is IResult.OnFailed -> {
                        showSnackbar("Failed\n${it.throwable.message}")
                        state = state.copy(
                            error = it.throwable
                        )
                    }
                    is IResult.OnLoading -> {
                        state = state.copy(
                            isLoading = it.isLoading,
                            isLoadingText = it.isLoadingText
                        )
                    }
                }
            }
    }
}