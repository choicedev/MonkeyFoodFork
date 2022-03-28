package com.choice.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.choice.theme.MonkeyTheme

@Composable
fun LikeAnimationButton(
    isFavorite: Boolean = false,
    onClick: () -> Unit,
) {

    val favIcon = if(isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder

    val favColor by animateColorAsState(
        targetValue = if(isFavorite) MonkeyTheme.colors.onError else MonkeyTheme.colors.surface
    )

    IconButton(
        modifier = Modifier
            .padding(MonkeyTheme.spacing.default)
            .size(MonkeyTheme.spacing.medium),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier
                .padding(MonkeyTheme.spacing.extraSmall)
                .size(MonkeyTheme.spacing.medium),
            imageVector = favIcon,
            contentDescription = "Localized icon",
            tint = favColor
        )
    }
}