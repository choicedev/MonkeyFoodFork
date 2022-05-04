package com.choice.recipes.ui.components

import androidx.compose.runtime.Composable
import com.choice.components.MonkeyIllustration
import com.choice.components.MonkeyIllustrationError
import com.choice.design.R
import com.choice.theme.MonkeyTheme

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