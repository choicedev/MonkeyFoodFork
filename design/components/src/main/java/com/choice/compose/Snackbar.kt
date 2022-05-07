package com.choice.compose

import androidx.compose.animation.*
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.choice.theme.MonkeyTheme

@Composable
fun MonkeyBar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier
) {

    var visible by remember{
        mutableStateOf(false)
    }
    val density = LocalDensity.current


    LaunchedEffect(key1 = true){
        visible = snackbarData.message.isNotEmpty()
    }

    AnimatedVisibility(
        visible,
        enter = expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(
            initialAlpha = 0.3f
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Bottom
        ) + fadeOut(
            targetAlpha = 0.3f
        )
    ) {
        Snackbar(
            snackbarData,
            modifier,
            false,
            MonkeyTheme.shapes.medium,
            MonkeyTheme.colors.primaryVariant,
            MonkeyTheme.colors.surface,
            SnackbarDefaults.primaryActionColor,
            MonkeyTheme.spacing.default
        )
    }
}