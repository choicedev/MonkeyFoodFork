package com.choice.core.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.choice.design.util.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<STATE, EVENT>(private val initState: STATE) : ViewModel() {

    @Suppress("MemberVisibilityCanBePrivate")
    protected val _state = mutableStateOf<STATE>(initState)
    val state: State<STATE> get() = _state

    @Suppress("MemberVisibilityCanBePrivate")
    protected val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    abstract fun onEvent(event: EVENT)


}