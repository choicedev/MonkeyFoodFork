package com.choice.recipes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.choice.animation.CircularLoadingBar
import com.choice.compose.MonkeyColumn
import com.choice.compose.MonkeyScaffold
import com.choice.design.util.UiEvent
import com.choice.recipes.ui.compose.LazyGridLayout
import com.choice.recipes.ui.compose.RecipeTopAppBar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun RecipesUI(
    navController: NavController,
    mainController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: RecipesViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        it.message
                    )
                }
            }
        }
    }

    if (!state.isLoading) {
        MonkeyScaffold(
            topBar = {
                RecipeTopAppBar(navController)
            },
        ) {
            MonkeyColumn {
                LazyGridLayout(scaffoldState, mainController)
            }
        }
    } else {
        MonkeyColumn(
            verticalArrangement = Arrangement.Center
        ) {
            CircularLoadingBar(
                text = state.isLoadingText
            )
        }
    }
}