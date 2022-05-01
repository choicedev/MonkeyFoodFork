package com.choice.theme.compose

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.choice.theme.MonkeyTheme

@Composable
fun monkeyTextFieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = Color.Transparent,
    unfocusedLabelColor = MonkeyTheme.colors.onSurface,
    focusedLabelColor = MonkeyTheme.colors.primaryVariant,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    textColor = MonkeyTheme.colors.onSurface,
    cursorColor = MonkeyTheme.colors.primaryVariant,
    placeholderColor = MonkeyTheme.colors.primaryVariant,
    disabledPlaceholderColor = MonkeyTheme.colors.onSurface
)