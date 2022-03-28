package com.choice.components

import android.accounts.AuthenticatorDescription
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.choice.theme.MonkeyTheme

@Composable
fun MonkeyIconButton(
    icon: ImageVector,
    color: Color = MonkeyTheme.colors.primaryVariant,
    description: String? = null,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = description ?: "Localized icon",
            tint = color,
        )
    }
}