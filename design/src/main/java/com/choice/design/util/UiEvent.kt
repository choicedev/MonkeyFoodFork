package com.choice.design.util

sealed class UiEvent{
    data class ShowSnackbar(val message: String): UiEvent()
    data class ShowDialog(val title: String, val message: String): UiEvent()
}