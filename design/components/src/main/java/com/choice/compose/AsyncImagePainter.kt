package com.choice.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.choice.design.R.*
import com.choice.theme.compositedOnSurface

private const val durationMillis = 400
private val DEFAULT_CORNERS = RoundedCornersTransformation(10f)


@Composable
fun MonkeyImage(
    urlImage: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    corners: RoundedCornersTransformation = DEFAULT_CORNERS,
    placeholderColor: Color? = MaterialTheme.colors.compositedOnSurface(0.2f)
) {
    val painter = rememberAsyncRecipeImagePainter(
        urlImage,
        corners = corners
    )

    Image(
        painter = painter,
        modifier = modifier.fillMaxSize(),
        contentDescription = "Localized image",
        contentScale = contentScale,
        alignment = Alignment.Center
    )

    if (painter.state is AsyncImagePainter.State.Loading && placeholderColor != null) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .backgroundClip(placeholderColor, RoundedCornerShape(10f))
        )
    }

}

@Composable
fun rememberAsyncRecipeImagePainter(
    data: Any?,
    corners: RoundedCornersTransformation = DEFAULT_CORNERS,
) = rememberAsyncImagePainter(
    model = ImageRequest.Builder(LocalContext.current)
        .data(data)
        .crossfade(true)
        .crossfade(durationMillis)
        .transformations(
            corners
        )
        /*.placeholder(drawable.il_image_preloader)*/
        .build(),
    contentScale = ContentScale.Crop,
)