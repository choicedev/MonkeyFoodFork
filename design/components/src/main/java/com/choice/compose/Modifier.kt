package com.choice.compose

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.choice.theme.MonkeyTheme

@Composable
fun Modifier.backgroundClip(
    brush: Brush,
    shape: Shape = MonkeyTheme.shapes.medium,
    alpha: Float = 1.0f
) = this
    .background(brush = brush, shape = shape, alpha = alpha)
    .clip(shape)

@Composable
fun Modifier.backgroundClip(
    color: Color,
    shape: Shape = MonkeyTheme.shapes.medium,
) = this
    .background(color = color, shape = shape)
    .clip(shape)


fun Modifier.scrim(colors: List<Color>): Modifier = drawWithContent {
    drawContent()
    drawRect(Brush.verticalGradient(colors))
}
