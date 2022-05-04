package com.choice.favorite.ui

import com.choice.core.viewModel.BaseViewModel
import com.choice.favorite.utils.FavoriteEvent
import com.choice.favorite.utils.FavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(

): BaseViewModel<FavoriteState, FavoriteEvent>(FavoriteState()) {
    override fun onEvent(event: FavoriteEvent) {
        TODO("Not yet implemented")
    }
}