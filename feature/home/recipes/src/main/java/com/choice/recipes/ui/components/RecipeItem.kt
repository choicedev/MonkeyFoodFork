package com.choice.recipes.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImagePainter
import com.choice.animation.DefaultShimmerAnimation
import com.choice.components.MonkeyImageRecipe
import com.choice.compose.*
import com.choice.model.Recipe
import com.choice.theme.MonkeyTheme
import com.google.accompanist.insets.imePadding

@Composable
fun RecipeItem(
    modifier: Modifier,
    item: Recipe?,
    onFavoriteClick: () -> Unit
) {

    item?.let {
        MonkeyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.Center
        ) {
            Recipe(it, onFavoriteClick)
        }
    }
}


@Composable
private fun Recipe(
    item: Recipe,
    onFavoriteClick: () -> Unit,
) {
    MonkeyDefaultColumn(
        modifier = Modifier
            .wrapContentSize()
            .imePadding(),
        verticalArrangement = Arrangement.Center

    ) {
        MonkeyImageRecipe(
            url = item.image,
            visible = item.favorite,
            favoriteClick = onFavoriteClick
        )
        RecipeText(
            item.title ?: "Title not found",
            item.dateAdded ?: "00/00/0000",
            item.publisher ?: "Not found publisher",
            modifier = Modifier.paddingFromBaseline(
                top = MonkeyTheme.spacing.extraSmall
            )
        )
        Spacer(Modifier.height(MonkeyTheme.spacing.medium))
    }
}

@Composable
private fun LoadingItem() {

    DefaultShimmerAnimation(cardSize = 200)
    Spacer(Modifier.height(MonkeyTheme.spacing.small))
    DefaultShimmerAnimation(cardSize = 15)
    Spacer(Modifier.height(MonkeyTheme.spacing.extraSmall))
    DefaultShimmerAnimation(cardSize = 20)
    Spacer(Modifier.height(MonkeyTheme.spacing.extraSmall))
    DefaultShimmerAnimation(cardSize = 15)
    Spacer(Modifier.height(MonkeyTheme.spacing.extraSmall))

}


@Composable
private fun RecipeText(
    title: String,
    date: String,
    by: String,
    modifier: Modifier = Modifier,
) {
    MonkeyDefaultColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Spacer(Modifier.height(MonkeyTheme.spacing.small))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(MonkeyTheme.spacing.small),
            text = date,
            style = MonkeyTheme.typography.subtitle2.copy(
                color = MonkeyTheme.colors.primary
            ),
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(MonkeyTheme.spacing.mediumSmall))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(MonkeyTheme.spacing.small),
            text = title,
            style = MonkeyTheme.typography.body2.copy(
                color = MonkeyTheme.colors.onSurface
            ),
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(MonkeyTheme.spacing.mediumSmall))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(MonkeyTheme.spacing.medium),
            text = "by ${by?.capitalize()}",
            style = MonkeyTheme.typography.subtitle2.copy(
                color = MonkeyTheme.colors.onSurface.copy(
                    alpha = 0.5f
                )
            ),
            overflow = TextOverflow.Ellipsis
        )
    }
}