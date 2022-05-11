package com.choice.recipes.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.choice.animation.LikeAnimationButton
import com.choice.compose.*
import com.choice.theme.MonkeyTheme

@Composable
fun MonkeyImageRecipe(
    url: String?,
    shapes: CornerBasedShape = MonkeyTheme.shapes.medium,
    visible: Boolean = false,
    modifier: Modifier = Modifier,
    favoriteClick: () -> Unit,
) {

    Box(
        modifier = modifier.size(MonkeyTheme.spacing.exxxztraLarge),
        contentAlignment = Alignment.TopEnd
    ) {
        MonkeyImage(
            urlImage = url,
            modifier = Modifier
                .clip(shapes),
        )
        MonkeyRow(
            modifier = Modifier.padding(MonkeyTheme.spacing.small),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            MonkeySurface(
                modifier = Modifier.size(MonkeyTheme.spacing.medium),
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
