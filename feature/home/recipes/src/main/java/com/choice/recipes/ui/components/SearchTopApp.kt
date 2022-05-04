package com.choice.recipes.ui.components

import androidx.compose.animation.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.East
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.choice.components.MonkeyCenterSearchTopAppBar
import com.choice.components.MonkeyIconButton
import com.choice.recipes.ui.RecipesViewModel
import com.choice.recipes.util.RecipesEvent


@Composable
fun SearchTopAppBar(
    visible: Boolean,
    onClick: () -> Unit
) {
    val density = LocalDensity.current

    AnimatedVisibility(
        visible = !visible,
        enter = slideInVertically {
            with(density) { -15.dp.roundToPx() }
        } + fadeIn(),
        exit = shrinkVertically {
            with(density) { -15.dp.roundToPx() }
        } + fadeOut()
    ) {
        SearchTopApp(
            onClick
        )
    }
}

@Composable
private fun SearchTopApp(
    onClick: () -> Unit,
    viewModel: RecipesViewModel = hiltViewModel()
) {
    val state = viewModel.state

    MonkeyCenterSearchTopAppBar(
        value = state.searchQuery,
        onValueChange = {
            viewModel.onEvent(RecipesEvent.OnSearchQueryChange(it))
        },
        placeholder = "Search recipe name or author",
        actions = {
            MonkeyIconButton(
                icon = Icons.Filled.East,
                onClick = onClick
            )
        }
    )
}