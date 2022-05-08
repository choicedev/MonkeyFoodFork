package com.choice.recipedetail.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import com.choice.compose.MonkeyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.choice.compose.IconText
import com.choice.theme.MonkeyTheme


@Composable
fun Description(
    title: String?,
    author: String?,
    date: String?,
    ingredients: List<String>?,
) {
    MonkeyColumn(
        modifier = Modifier
            .padding(top = MonkeyTheme.spacing.small, bottom = MonkeyTheme.spacing.medium)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = MonkeyTheme.spacing.extraLarge),
            text = "$title",
            style = MonkeyTheme.typography.h6.copy(
                fontWeight = FontWeight.SemiBold,
                color = MonkeyTheme.colors.onSurface.copy(0.8f)
            ),
            overflow = TextOverflow.Ellipsis
        )

        IconText(
            modifier = Modifier
                .fillMaxWidth()
                .paddingFromBaseline(top = MonkeyTheme.spacing.large),
            text = author?.capitalize().toString(),
            textStyle = MonkeyTheme.typography.subtitle1,
            icon = Icons.Filled.Face
        )

        IconText(
            modifier = Modifier
                .fillMaxWidth()
                .paddingFromBaseline(top = MonkeyTheme.spacing.large),
            text = date?.capitalize().toString(),
            textStyle = MonkeyTheme.typography.subtitle1,
            icon = Icons.Filled.Today
        )

        IconText(
            modifier = Modifier
                .fillMaxWidth()
                .paddingFromBaseline(top = MonkeyTheme.spacing.large),
            text = "Ingredients",
            textStyle = MonkeyTheme.typography.subtitle1,
            icon = Icons.Filled.List
        )
        Spacer(Modifier.paddingFromBaseline(top = MonkeyTheme.spacing.small))
        ingredients?.forEachIndexed { index, item ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MonkeyTheme.spacing.extraSmall),
                text = "●  ${item.capitalize()}",
                style = MonkeyTheme.typography.subtitle2.copy(
                    fontWeight = FontWeight.Normal,
                    color = MonkeyTheme.colors.onSurface
                )
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MonkeyTheme.colors.onSurface.copy(0.4f),
            )
        }
    }
}