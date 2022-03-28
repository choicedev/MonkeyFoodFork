package com.choice.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.choice.compose.MonkeyColumn
import com.choice.compose.MonkeyScaffold
import com.choice.design.util.UiEvent
import com.choice.home.HomeViewModel
import com.choice.home.ui.components.HomeTopAppBar
import com.choice.home.ui.compose.LazyGrid
import com.choice.theme.MonkeyTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeUI(
    navController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is UiEvent.ShowSnackbar -> {
                    when(it.message){

                    }
                }
            }
        }
    }

    MonkeyScaffold(
        topBar = {
            HomeTopAppBar()
        },
    ) {
        MonkeyColumn(
            verticalArrangement = if(state.isLoading || state.recipeList.isEmpty()) Arrangement.Center else Arrangement.Top
        ){
            LazyGrid(scaffoldState)
        }
    }
}