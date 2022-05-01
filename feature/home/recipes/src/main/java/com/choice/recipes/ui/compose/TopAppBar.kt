package com.choice.recipes.ui.compose

import androidx.compose.animation.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.choice.components.MonkeyCenterTopAppBarBack
import com.choice.components.MonkeyIconButton
import com.choice.recipes.ui.RecipesViewModel
import com.choice.recipes.util.RecipesEvent
import com.choice.recipes.ui.components.SearchToolBar
import com.choice.theme.MonkeyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RecipeTopAppBar(
    viewModel: RecipesViewModel
) {

    var visible by remember {
        mutableStateOf(viewModel.state.searchQuery.isEmpty())
    }
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()


    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            with(density) { -40.dp.roundToPx() }
        } + fadeIn(),
        exit = shrinkVertically {
            with(density) { -40.dp.roundToPx() }
        } + fadeOut()
    ) {
        MonkeyCenterTopAppBarBack(
            title = "MonkeyFood",
            textColor = MonkeyTheme.colors.primaryVariant,
            actions = {
                MonkeyIconButton(icon = Icons.Filled.Search) {
                    visible = false
                }
            }
        )
    }

    SearchToolBar(
        visible = visible,
        viewModel = viewModel,
        onClick = {
            scope.launch {
                visible = true
                delay(150)
                viewModel.onEvent(RecipesEvent.OnSearchQueryChange(""))
            }
        }
    )

}
