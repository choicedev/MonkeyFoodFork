package com.choice.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver


val md_theme_light_primary = Color(0xFFa36a34)
val md_theme_light_onPrimary = Color(0xFFd79860)
val md_theme_light_onPrimaryVariant = Color(0xFF703f08)
val md_theme_light_secondary = Color(0xFF52361a)
val md_theme_light_onSecondary = Color(0xFF806042)
val md_theme_light_onSecondaryVariant = Color(0xFF2a1000)
val md_theme_light_error = Color(0xFFba1b1b)
val md_theme_light_onError = Color(0xFFE53935)
val md_theme_light_background = Color(0xFFFFE8DD)
val md_theme_light_onBackground = Color(0xFF1b1b1d)
val md_theme_light_surface = Color(0xFFfdfbff)
val md_theme_light_onSurface = Color(0xFF1b1b1d)

val seed = Color(0xFF003166)
val error = Color(0xFFba1b1b)

val green = Color(0xFF1b9f47)
val red = Color(0xFFE53935)

@Composable
fun Colors.compositedOnSurface(alpha: Float): Color {
    return onSurface.copy(alpha = alpha).compositeOver(surface)
}