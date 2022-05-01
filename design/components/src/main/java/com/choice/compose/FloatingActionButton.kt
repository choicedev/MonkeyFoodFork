package com.choice.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import com.choice.theme.MonkeyTheme

/**
 * Monkey floating action button
 *
 * @param onClick
 * @param modifier
 * @param interactionSource
 * @param shape
 * @param backgroundColor
 * @param contentColor
 * @param elevation
 * @param icon
 * @receiver
 */
@Composable
fun MonkeyFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MonkeyTheme.shapes.small.copy(CornerSize(percent = 50)),
    backgroundColor: Color = MonkeyTheme.colors.secondary,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    icon: ImageVector
) {
    FloatingActionButton(
        onClick,
        modifier,
        interactionSource,
        shape,
        backgroundColor,
        contentColor,
        elevation,
        content = {
            Icon(
                imageVector = icon,
                contentDescription = "Localized icon"
            )
        }
    )
}