package com.choice.recipes.ui.compose

import androidx.compose.animation.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.choice.compose.MonkeyCenterTopAppBar
import com.choice.components.MonkeyIconButton
import com.choice.recipes.ui.RecipesViewModel
import com.choice.recipes.ui.components.SearchTopAppBar
import com.choice.recipes.util.RecipesEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RecipeTopAppBar(
    navController: NavController,
    viewModel: RecipesViewModel = hiltViewModel(),
) {

    var visible by remember {
        mutableStateOf(viewModel.state.searchQuery.isEmpty())
    }
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            with(density) { -15.dp.roundToPx() }
        } + fadeIn(),
        exit = shrinkVertically {
            with(density) { -15.dp.roundToPx() }
        } + fadeOut()
    ) {
        MonkeyCenterTopAppBar(
            title = "MonkeyFood",
            actions = {
                MonkeyIconButton(icon = Icons.Filled.Search) {
                    visible = false
                }
            }
        )
    }

    SearchTopAppBar(
        visible = visible,
        onClick = {
            scope.launch {
                visible = true
                delay(150)
                viewModel.onEvent(RecipesEvent.OnSearchQueryChange(""))
            }
        }
    )

}