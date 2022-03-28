package com.choice.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.choice.compose.MonkeyDefaultColumn
import com.choice.theme.MonkeyTheme

@Composable
fun MonkeyIllustration(
    modifier: Modifier = Modifier,
    text: String,
    illustration: Int,
    size: Dp,
) {
    MonkeyDefaultColumn(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.size(size),
            painter = painterResource(illustration),
            contentDescription = "Illustration localized",
        )
        Spacer(
            modifier = Modifier.width(
                MonkeyTheme.spacing.small
            )
        )
        Text(
            text = text,
            style = MonkeyTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Normal,
            ),
            overflow = TextOverflow.Ellipsis
        )
    }
}