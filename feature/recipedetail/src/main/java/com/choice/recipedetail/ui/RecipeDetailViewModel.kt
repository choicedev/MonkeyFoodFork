package com.choice.recipedetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.choice.core.viewModel.BaseViewModel
import com.choice.model.Recipe
import com.choice.recipedetail.util.RecipeDetailEvent
import com.choice.recipedetail.util.RecipeState
import com.choice.usecase.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<RecipeState, RecipeDetailEvent>(RecipeState()) {

    private var currentId: Int? = null

    private var recipeJob: Job? = null

    init {
        savedStateHandle.get<Int>("recipeId")?.let {
            if (it != -1) {
                viewModelScope.launch {
                    currentId = it
                    onGetRecipeById()
                }
            }
        }
    }

    override fun onEvent(event: RecipeDetailEvent) {
        when (event) {
            is RecipeDetailEvent.OnFavoriteChange -> {
                viewModelScope.launch {
                    onFavoriteChange()
                    delay(50)
                    onGetRecipeById()
                }
            }
        }
    }


    private fun onFavoriteChange(
        recipe: Recipe = state.recipe,
    ) = viewModelScope.launch {
        recipeUseCases.setFavorite(recipe)
    }

    private suspend fun onGetRecipeById(
        id: Int = currentId!!
    ) {
        state = state.copy(
            recipe = recipeUseCases.getRecipeById(id) ?: Recipe()
        )
    }
}