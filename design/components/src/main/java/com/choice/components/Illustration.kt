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
import com.choice.design.R
import com.choice.theme.MonkeyTheme
import java.net.UnknownHostException

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
                MonkeyTheme.spacing.medium
            )
        )
        Text(
            text = text,
            style = MonkeyTheme.typography.body1.copy(
                fontWeight = FontWeight.Normal,
            ),
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun MonkeyIllustrationError(
    modifier: Modifier = Modifier,
    error: Throwable,
) {
    MonkeyDefaultColumn(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        when (error) {
            is UnknownHostException -> {
                MonkeyIllustration(
                    text = "See if you are connected to the internet.",
                    illustration = R.drawable.il_no_connection,
                    size = MonkeyTheme.spacing.exxxtraLarge
                )
            }

            else -> {
                MonkeyIllustration(
                    text = "Report the error to the developer:\n${error.message}",
                    illustration = R.drawable.il_cooking,
                    size = MonkeyTheme.spacing.exxxtraLarge
                )
            }
        }
    }
}