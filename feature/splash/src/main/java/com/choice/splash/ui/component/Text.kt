package com.choice.splash.ui.component

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.choice.compose.MonkeyRow
import com.choice.design.R
import com.choice.theme.MonkeyTheme

@Composable
fun MonkeyTitle(
    visible: Boolean = true
) {
    AnimatedVisibility(
        visible = visible,
        enter = expandHorizontally(
            expandFrom = Alignment.End,
            animationSpec = tween(1000)
        ) + fadeIn(),
        exit = shrinkHorizontally(
            shrinkTowards = Alignment.Start,
            animationSpec = tween(1000)
        ) + fadeOut(
            targetAlpha = 0.8f
        )
    ) {
        MonkeyRow(
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MonkeyTheme.colors.onPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("Monkey")
                    }

                    withStyle(
                        style = SpanStyle(color = MonkeyTheme.colors.onSurface)
                    ) {
                        append("Food")
                    }
                },
                style = MonkeyTheme.typography.h3
            )
            Spacer(Modifier.width(MonkeyTheme.spacing.extraSmall))
            Icon(
                imageVector = Icons.Filled.RestaurantMenu,
                contentDescription = "Localized icon")
        }
    }
}