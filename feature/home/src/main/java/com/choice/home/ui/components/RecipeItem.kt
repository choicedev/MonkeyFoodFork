package com.choice.home.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.choice.animation.LikeAnimationButton
import com.choice.components.MonkeyImageCoil
import com.choice.compose.MonkeyColumn
import com.choice.compose.MonkeyDefaultColumn
import com.choice.compose.MonkeyRow
import com.choice.compose.MonkeySurface
import com.choice.model.Recipe
import com.choice.theme.MonkeyTheme

@Composable
fun RecipeItem(
    item: Recipe,
    onClick: () -> Unit
) {

    MonkeyColumn(
        horizontalAlignment = Alignment.Start
    ) {
        RecipeImage(
            url = item.image,
            visible = item.favorite,
            favoriteClick = onClick
        )
        Spacer(Modifier.height(MonkeyTheme.spacing.extraSmall))
        RecipeText(
            item.title,
            item.dateAdded,
            item.publisher
        )
        Spacer(Modifier.height(MonkeyTheme.spacing.medium))
    }
}


@Composable
private fun RecipeText(
    title: String?,
    date: String?,
    by: String?,
) {
    MonkeyDefaultColumn(
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(MonkeyTheme.spacing.small),
            text = "$date",
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
            text = title ?: "No Title",
            style = MonkeyTheme.typography.subtitle1.copy(
                color = MonkeyTheme.colors.onSurface
            ),
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(MonkeyTheme.spacing.mediumSmall))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(MonkeyTheme.spacing.small),
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

@Composable
private fun RecipeImage(
    url: String?,
    visible: Boolean = false,
    favoriteClick: () -> Unit,
) {

    Box(
        modifier = Modifier.size(MonkeyTheme.spacing.exxtraLarge)
    ) {
        MonkeyImageCoil(
            modifier = Modifier.fillMaxSize(),
            url = url
        )

        MonkeyRow(
            modifier = Modifier.padding(MonkeyTheme.spacing.small),
            horizontalArrangement = Arrangement.End
        ) {
            MonkeySurface(
                color = MonkeyTheme.colors.onSurface.copy(
                    alpha = 0.86f
                ),
                shape = CircleShape
            ) {
                LikeAnimationButton(
                    isFavorite = visible,
                    onClick = favoriteClick
                )
            }
        }
    }
}