package com.choice.recipedetail.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.transform.RoundedCornersTransformation
import com.choice.animation.LikeAnimationButton
import com.choice.compose.MonkeyImage
import com.choice.compose.MonkeyRow
import com.choice.compose.scrim
import com.choice.recipedetail.ui.compose.RecipeDetailFlaotingButton
import com.choice.recipedetail.ui.compose.RecipeTopAppBar
import com.choice.theme.MonkeyTheme

@Composable
fun Header(
    urlImage: String?,
    isFavorite: Boolean?,
    navController: NavController,
    favoriteClick: () -> Unit
) {

    Box {
        MonkeyImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .scrim(colors = listOf(Color(0x80000000), Color(0x1B000000))),
            corners = RoundedCornersTransformation(0f),
            urlImage = urlImage ?: ""
        )

        RecipeTopAppBar(
            navController = navController
        )

        MonkeyRow(
            modifier = Modifier
                .align(Alignment.BottomStart)
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MonkeyTheme.colors.secondary,
                thickness = MonkeyTheme.spacing.extraSmall
            )
        }

        RecipeDetailFlaotingButton(
            isFavorite = isFavorite ?: false,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(y = 25.dp)
                .padding(end = MonkeyTheme.spacing.mediumSmall),
            onClick = favoriteClick
        )

    }
}