package com.choice.home.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.East
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.choice.components.MonkeyIconButton
import com.choice.compose.MonkeyRow
import com.choice.compose.MonkeySurface
import com.choice.compose.MonkeyTextFieldState
import com.choice.theme.MonkeyTheme
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.insets.systemBarsPadding


@Composable
fun SearchToolBar(
    visible: Boolean,
    onClick: () -> Unit
) {
    val density = LocalDensity.current

    val text = rememberSaveable{
        mutableStateOf("")
    }

    AnimatedVisibility(
        visible = !visible,
        enter = slideInVertically {
            with(density) { 40.dp.roundToPx() }
        } + fadeIn(),
        exit = shrinkVertically {
            with(density) { -40.dp.roundToPx() }
        } + fadeOut()
    ) {
        MonkeySurface(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            SearchText(
                value = text,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun SearchText(
    value: MutableState<String>,
    enable: Boolean = true,
    onClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    MonkeyRow(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(
                top = MonkeyTheme.spacing.small
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        MonkeyTextFieldState(
            modifier = Modifier.fillMaxWidth(),
            valueState = value,
            label = "Search",
            enabled = enable,
            maxLength = 20,
            trailingIcon = {
                MonkeyIconButton(
                    icon = Icons.Filled.East,
                    onClick = onClick
                )
            },
            imeAction = ImeAction.Search,
            onAction = KeyboardActions {
                keyboardController?.hide()
                onClick()
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MonkeyTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}