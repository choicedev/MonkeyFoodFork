package com.choice.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Elevation(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
)

val LocalElevation = compositionLocalOf { Elevation() }