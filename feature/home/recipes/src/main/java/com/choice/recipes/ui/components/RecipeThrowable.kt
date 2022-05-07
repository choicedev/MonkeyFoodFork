package com.choice.recipes.ui.components

import androidx.compose.runtime.Composable
import com.choice.components.MonkeyIllustrationError

@Composable
fun RecipeThrowable(
    error: Throwable?
) {
    error?.let {
        MonkeyIllustrationError(
            error = error
        )
    }
}