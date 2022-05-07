package com.choice.recipes.ui

import androidx.lifecycle.viewModelScope
import com.choice.core.util.IResult
import com.choice.core.viewModel.BaseViewModel
import com.choice.model.Recipe
import com.choice.recipes.util.RecipesEvent
import com.choice.recipes.util.RecipesState
import com.choice.usecase.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases
) : BaseViewModel<RecipesState, RecipesEvent>(RecipesState()) {

    private var recentFavoriteItem: Recipe? = null

    private var getRecipesJob: Job? = null

    init {
        viewModelScope.launch {
            println("get recipes: ${getRecipes()}")
            getRecipes()
        }
    }

    override fun onEvent(event: RecipesEvent) {
        when (event) {
            is RecipesEvent.OnFavoriteChange -> {
                viewModelScope.launch {
                    onFavoriteChange(event.recipe)
                    recentFavoriteItem = event.recipe
                }
            }

            is RecipesEvent.OnRestoreFavorite -> {
                viewModelScope.launch {
                    recentFavoriteItem?.let {
                        onFavoriteChange(it)
                        return@launch
                    }
                    showSnackbar("Item not found")
                }
            }

            is RecipesEvent.OnSearchQueryChange -> {
                viewModelScope.launch {
                    state = state.copy(searchQuery = event.query)
                    onSearchChange()
                }
            }
        }
    }

    private suspend fun onFavoriteChange(recipe: Recipe) {
        recipeUseCases.setFavorite(recipe)
    }

    private suspend fun onSearchChange(query: String = state.searchQuery.lowercase()) {
        recipeUseCases.searchQuery(query)
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
                            isLoadingText = result.message
                        )
                    }

                    is IResult.OnFailed -> {
                        showSnackbar("${result.throwable.message}")
                    }

                }
            }
    }

    private suspend fun getRecipes() {
        getRecipesJob?.cancel()
        getRecipesJob = recipeUseCases.getRecipes(Unit)
            .onEach {
                when (it) {
                    is IResult.OnSuccess -> {
                        if(state.searchQuery.isEmpty()) {
                            state = state.copy(
                                recipeList = it.response
                            )
                        }else{
                            onSearchChange()
                        }
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
                            isLoadingText = it.message
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}