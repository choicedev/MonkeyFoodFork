package com.choice.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.choice.compose.MonkeySurface
import com.choice.theme.MonkeyTheme

@Composable
fun MonkeyBackgroundUI(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    MonkeySurface(
        modifier = modifier.fillMaxSize(),
        shape = MonkeyTheme.shapes.small,
        color = MonkeyTheme.colors.background,
        content = content
    )
}