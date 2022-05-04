package com.choice.core.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.choice.design.util.UiEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, EVENT>(private val initState: STATE) : ViewModel() {

    var state by mutableStateOf(initState)

    @Suppress("MemberVisibilityCanBePrivate")
    protected val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    abstract fun onEvent(event: EVENT)

    suspend fun showSnackbar(message: String) = _eventFlow.emit(UiEvent.ShowSnackbar(message))
}


abstract class BaseViewModelSheet<STATE, EVENT, BOTTOMSCREEN, BOTTOMEVENT>(initState: STATE) :
    ViewModel() {

    @Suppress("MemberVisibilityCanBePrivate")
    protected val _state = mutableStateOf(initState)
    val state: State<STATE> get() = _state

    @Suppress("MemberVisibilityCanBePrivate")
    protected val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    @Suppress("MemberVisibilityCanBePrivate")
    protected val _bottomSheetFlow = MutableSharedFlow<BOTTOMSCREEN>()
    val bottomScreenFlow = _bottomSheetFlow.asSharedFlow()

    abstract fun onEvent(event: EVENT)

    abstract fun onBottomSheetEvent(event: BOTTOMEVENT)

    suspend fun onBottomSheetScreen(screen: BOTTOMSCREEN) = _bottomSheetFlow.emit(screen)

}