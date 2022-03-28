package com.choice.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.choice.theme.MonkeyTheme

@Composable
fun MonkeyImageCoil(
    url: String?,
    crossfade: Boolean = true,
    durationMillis: Int = 400,
    shapes: RoundedCornersTransformation = RoundedCornersTransformation(10f),
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url ?: "")
            .crossfade(crossfade)
            .crossfade(durationMillis)
            .transformations(shapes)
            .build(),
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(MonkeyTheme.shapes.medium),
        contentDescription = "Localized image"
    )
}