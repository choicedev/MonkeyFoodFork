package com.choice.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.choice.core.util.IResult
import com.choice.core.viewModel.BaseViewModel
import com.choice.design.util.UiEvent
import com.choice.home.util.HomeEvent
import com.choice.home.util.HomeState
import com.choice.model.FavoriteChange
import com.choice.model.RecipeRequest
import com.choice.usecase.IRecipeGetUseCase
import com.choice.usecase.IRecipeSetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recipeGetUseCases: IRecipeGetUseCase,
    private val recipeSetFavoriteUseCase: IRecipeSetFavoriteUseCase
) : BaseViewModel<HomeState, HomeEvent>(HomeState()) {

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            getRecipes(1)
        }
    }

    override fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.Favorite -> {
                    recipeSetFavoriteUseCase(
                        FavoriteChange(event.id, event.favorite)
                    ).catch {
                        _eventFlow.emit(UiEvent.ShowSnackbar("$it"))
                    }
                        .collect {
                            when(it){
                                is IResult.OnSuccess -> {
                                    getRecipes(1)
                                }
                                is IResult.OnFailed -> {
                                    _eventFlow.emit(UiEvent.ShowSnackbar("Failed\n${it.throwable}"))
                                }
                            }
                        }
                }
            }
        }
    }

    suspend fun getRecipes(page: Int){
        recipeGetUseCases(RecipeRequest(page))
            .catch {
                _eventFlow.emit(UiEvent.ShowSnackbar("$it"))
            }
            .collect {
                when(it){
                    is IResult.OnSuccess -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            recipeList = it.response
                        )
                        Log.i("TAG INFO", "${it.response}")
                    }
                    is IResult.OnFailed -> {
                        _state.value = state.value.copy(
                            isLoading = false
                        )
                        _eventFlow.emit(UiEvent.ShowSnackbar("Failed\n${it.throwable}"))
                    }
                }
            }
    }
}