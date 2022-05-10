package com.choice.favorites.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.transform.RoundedCornersTransformation
import com.choice.components.MonkeyImageRecipe
import com.choice.compose.*
import com.choice.model.Recipe
import com.choice.theme.MonkeyDeliveryTheme
import com.choice.theme.MonkeyTheme


@Composable
 fun FavoriteItem(
    item: Recipe,
    onSelect: () -> Unit
) {

    MonkeyCard(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onSelect,
        enabled = true,
        shape = MonkeyTheme.shapes.medium
    ) {
        MonkeyDefaultRow(
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .height(MonkeyTheme.spacing.extraLarge)
                    .width(MonkeyTheme.spacing.extraLarge)
            ) {
                MonkeyImage(
                    urlImage = item.image,
                    corners = RoundedCornersTransformation(0f)
                )
            }
            FavoriteText(item.title, item.dateAdded, item.publisher)
        }
    }
}

@Composable
private fun FavoriteText(
    title: String?,
    date: String?,
    publisher: String?,
){
        MonkeyColumn(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = title ?: "Title not found",
                style = MonkeyTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MonkeyTheme.colors.secondary
                )
            )
            Spacer(Modifier.height(MonkeyTheme.spacing.small))
            Text(
                text = "by ${publisher?.capitalize()}",
                style = MonkeyTheme.typography.subtitle2.copy(
                    fontWeight = FontWeight.Normal,
                    color = MonkeyTheme.colors.primaryVariant
                )
            )
        }
}
