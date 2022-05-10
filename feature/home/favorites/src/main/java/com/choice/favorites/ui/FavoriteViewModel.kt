package com.choice.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.choice.core.util.IResult
import com.choice.core.viewModel.BaseViewModel
import com.choice.favorites.utils.FavoriteEvent
import com.choice.favorites.utils.FavoriteState
import com.choice.usecase.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases
) : BaseViewModel<FavoriteState, FavoriteEvent>(FavoriteState()) {

    private var getFavoritesJob: Job? = null

    init{
        viewModelScope.launch {
            getFavorites()
        }
    }

    private suspend fun getFavorites() {
        getFavoritesJob?.cancel()
        getFavoritesJob = recipeUseCases.getFavorites(Unit)
            .onEach {
            when(it){
                is IResult.OnSuccess -> {
                    state = state.copy(
                        favoriteList = it.response
                    )
                }

                is IResult.OnFailed -> {
                    showSnackbar(it.throwable.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun onEvent(event: FavoriteEvent) {
    }

}