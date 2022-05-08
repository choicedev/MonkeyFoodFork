package com.choice.theme.compose

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.choice.theme.MonkeyTheme

@Composable
fun monkeyTextFieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = Color.Transparent,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    textColor = MonkeyTheme.colors.primaryVariant,
    cursorColor = MonkeyTheme.colors.primary,
    placeholderColor = MonkeyTheme.colors.primaryVariant,
    disabledPlaceholderColor = MonkeyTheme.colors.onSurface
)