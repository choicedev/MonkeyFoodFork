package com.choice.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.choice.theme.MonkeyTheme

@Composable
fun MonkeyCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = MonkeyTheme.shapes.medium,
    backgroundColor: Color = MonkeyTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = MonkeyTheme.elevation.extraSmall,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication? = LocalIndication.current,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        border = null,
        elevation = elevation,
        interactionSource = interactionSource,
        indication = indication,
        enabled = enabled,
        onClickLabel = null,
        role = null,
        content = content
    )
}