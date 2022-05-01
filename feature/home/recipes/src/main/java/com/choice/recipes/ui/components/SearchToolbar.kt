package com.choice.recipes.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.East
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.choice.components.MonkeyIconButton
import com.choice.compose.MonkeyRow
import com.choice.compose.MonkeyTextField
import com.choice.recipes.ui.RecipesViewModel
import com.choice.recipes.util.RecipesEvent
import com.choice.theme.compose.monkeyTextFieldColors


@Composable
fun SearchToolBar(
    visible: Boolean,
    viewModel: RecipesViewModel,
    onClick: () -> Unit
) {
    val density = LocalDensity.current

    AnimatedVisibility(
        visible = !visible,
        enter = slideInVertically {
            with(density) { 40.dp.roundToPx() }
        } + fadeIn(),
        exit = shrinkVertically() {
            with(density) { -40.dp.roundToPx() }
        } + fadeOut()
    ) {
        SearchText(
            viewModel,
            onClick = onClick
        )
    }
}

@Composable
private fun SearchText(
    viewModel: RecipesViewModel,
    enable: Boolean = true,
    onClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val state = viewModel.state
    MonkeyRow(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        MonkeyTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(RecipesEvent.OnSearchQueryChange(it))
            },
            placeholder = "Search recipe name or author",
            enabled = enable,
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
            maxLines = 1,
            singleLine = true,
            colors = monkeyTextFieldColors()
        )
    }
}