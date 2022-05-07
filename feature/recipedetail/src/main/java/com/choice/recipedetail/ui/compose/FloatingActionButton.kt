package com.choice.recipedetail.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.choice.animation.LikeAnimationButton
import com.choice.theme.MonkeyTheme


@Composable
fun RecipeDetailFlaotingButton(
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        FloatingActionButton(
            modifier = Modifier
                .size(MonkeyTheme.spacing.largeSmall),
            shape = RoundedCornerShape(20f),
            onClick = onClick
        ) {
            LikeAnimationButton(
                isFavorite = isFavorite,
                size = MonkeyTheme.spacing.large,
                onClick = onClick
            )
        }
    }
}