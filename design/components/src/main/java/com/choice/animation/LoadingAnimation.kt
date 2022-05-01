package com.choice.animation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.choice.compose.MonkeyDefaultColumn
import com.choice.theme.MonkeyTheme

@Composable
fun CircularLoadingBar(
    color: Color = MonkeyTheme.colors.primaryVariant
) {
    CircularProgressIndicator(
        color = color
    )
}

@Composable
fun CircularLoadingBar(
    modifier: Modifier = Modifier,
    text: String = "Waiting...",
    color: Color = MonkeyTheme.colors.primaryVariant
) {
    MonkeyDefaultColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = color
        )
        Spacer(Modifier.width(
            MonkeyTheme.spacing.extraSmall
            )
        )
        Text(
            text = text,
            style = MonkeyTheme.typography.subtitle1.copy(
                color = color
            )
        )
    }
}