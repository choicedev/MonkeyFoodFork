package com.choice.home.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.choice.animation.CircularLoadingBar
import com.choice.components.MonkeyIllustration
import com.choice.home.HomeViewModel
import com.choice.design.R
import com.choice.home.ui.components.RecipeItem
import com.choice.home.util.FAILED_FAVORITE
import com.choice.home.util.HomeEvent
import com.choice.home.util.SUCESS_FAVORITE
import com.choice.model.Recipe
import com.choice.theme.MonkeyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LazyGrid(
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel()
){
    val state by viewModel.state

    when {
        state.isLoading -> {
            CircularLoadingBar()
        }
        else -> {
            if(state.recipeList.isEmpty()){
                MonkeyIllustration(
                    text = "No container found",
                    illustration = R.drawable.il_void,
                    size = 250.dp
                )
            } else {
                LazyGridLayout(
                    scaffoldState = scaffoldState
                )
            }
        }
    }

}

@Composable
private fun LazyGridLayout(
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.state
    val scope = rememberCoroutineScope()
    val stateGrid = rememberLazyListState()

    LazyVerticalGrid(
        state = stateGrid,
        verticalArrangement = Arrangement.Top,
        horizontalArrangement = Arrangement.Center,
        cells = GridCells.Fixed(2)){
        items(2){
            Spacer(
                Modifier
                    .height(
                        MonkeyTheme.spacing.large
                    )
            )
        }
            itemsIndexed(state.recipeList.toList()){_, item ->
                RecipeItem(item){
                    viewModel.onEvent(HomeEvent.Favorite(item.id!!, !item.favorite))
                    scope.launch {
                        delay(1000)
                        val result = scaffoldState.snackbarHostState.showSnackbar(
                            if(item.favorite) FAILED_FAVORITE else SUCESS_FAVORITE,
                            if(item.favorite) "Adicionar" else "Remover"
                        )
                        if(result == SnackbarResult.ActionPerformed){
                            viewModel.onEvent(HomeEvent.Favorite(item.id!!,
                                item.favorite
                            ))
                        }
                    }
                }
            }

        items(2){
            Spacer(
                Modifier
                    .height(
                        MonkeyTheme.spacing.extraLarge
                    )
            )
        }
        }
}