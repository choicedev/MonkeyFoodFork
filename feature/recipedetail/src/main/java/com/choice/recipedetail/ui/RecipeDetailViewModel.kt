package com.choice.recipedetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.choice.core.util.IResult
import com.choice.core.viewModel.BaseViewModel
import com.choice.model.Recipe
import com.choice.param.FavoriteParam
import com.choice.recipedetail.util.RecipeDetailEvent
import com.choice.recipedetail.util.RecipeState
import com.choice.usecase.RecipeGetUseCase
import com.choice.usecase.RecipeSetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeById: RecipeGetUseCase,
    private val recipeFavoriteUseCase: RecipeSetFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<RecipeState, RecipeDetailEvent>(RecipeState()) {

    init {
        savedStateHandle.get<Int>("recipeId")?.let {
            if (it != -1) {
                viewModelScope.launch {
                    state = state.copy(
                        id = it
                    )
                    onGetRecipeById()
                }
            }
        }
    }

    override fun onEvent(event: RecipeDetailEvent) {
        when (event) {
            is RecipeDetailEvent.OnFavoriteChange -> {
                onFavoriteChange()
            }
        }
    }


    private fun onFavoriteChange(
        id: Int? = state.recipe.id,
        favorite: Boolean = state.recipe.favorite
    ) = viewModelScope.launch {
        recipeFavoriteUseCase(
            FavoriteParam(id ?: -1, !favorite)
        ).catch { showSnackbar("$it") }
            .collect {
                when (it) {
                    is IResult.OnSuccess -> {
                        onGetRecipeById()
                    }
                    is IResult.OnFailed -> {
                        showSnackbar("Failed\n${it.throwable.message}")
                    }
                }
            }
    }

    private suspend fun onGetRecipeById(
        id: Int = state.id
    ) {
        getRecipeById(id)
            .catch { e ->
                showSnackbar("$e")
            }.collect { recipe ->
                when (recipe) {
                    is IResult.OnSuccess -> {
                        state = state.copy(
                            recipe = recipe.response
                        )
                    }
                    is IResult.OnFailed -> {
                        showSnackbar("FAILED ${recipe.throwable}")
                    }
                }
            }
    }
}