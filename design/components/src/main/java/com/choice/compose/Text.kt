package com.choice.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.choice.theme.MonkeyTheme
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun IconText(
    text: String,
    textStyle: TextStyle = MonkeyTheme.typography.subtitle2,
    icon: ImageVector,
    iconSize: Dp = 24.dp,
    color: Color = MonkeyTheme.colors.primaryVariant,
    modifier: Modifier = Modifier
) {
    MonkeyDefaultRow(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            imageVector = icon,
            tint = color,
            contentDescription = null,
        )
        Spacer(Modifier.padding(MonkeyTheme.spacing.extraSmall))
        Text(
            text = text,
            style = textStyle.copy(
                color = color
            ),
            overflow = TextOverflow.Ellipsis
        )
    }
}