package com.choice.recipes.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.choice.components.MonkeyIllustration
import com.choice.compose.MonkeyColumn
import com.choice.compose.StaggeredVerticalGrid
import com.choice.compose.backgroundClip
import com.choice.design.R
import com.choice.design.util.BottomNavItem.Companion.items
import com.choice.design.util.MonkeyScreen
import com.choice.recipes.ui.RecipesViewModel
import com.choice.recipes.ui.components.RecipeItem
import com.choice.recipes.ui.components.RecipeThrowable
import com.choice.recipes.util.FAILED_FAVORITE
import com.choice.recipes.util.RecipesEvent
import com.choice.recipes.util.SUCESS_FAVORITE
import com.choice.theme.MonkeyTheme
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.ceil


@Composable
fun LazyGridLayout(
    scaffoldState: ScaffoldState,
    mainController: NavController,
    viewModel: RecipesViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val scope = rememberCoroutineScope()


    when {
        state.error != null -> {
            RecipeThrowable(state.error)
        }

        state.recipeList.isEmpty() -> {
            MonkeyIllustration(
                text = "No recipe found",
                illustration = R.drawable.il_cooking,
                size = MonkeyTheme.spacing.exxxtraLarge
            )
        }


        else -> {
            MonkeyColumn(
                Modifier.verticalScroll(rememberScrollState())
            ) {
                LazyGridLayout {
                    state.recipeList.forEach { item ->
                        RecipeItem(
                            modifier = Modifier
                                .fillMaxSize()
                                .backgroundClip(Color.Transparent, MonkeyTheme.shapes.medium)
                                .clickable {
                                    mainController.navigate(MonkeyScreen.RecipeDetail.route + "?recipeId=${item.id}")
                                },
                            item,
                            onFavoriteClick = {
                                scope.launch {
                                    viewModel.onEvent(
                                        RecipesEvent.OnFavoriteChange(
                                            item
                                        )
                                    )
                                    delay(500)
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        if (item.favorite) FAILED_FAVORITE else SUCESS_FAVORITE,
                                        if (item.favorite) "Add" else "Undo"
                                    ).takeIf { result ->
                                        result == SnackbarResult.ActionPerformed
                                    }?.apply {
                                        viewModel.onEvent(RecipesEvent.OnRestoreFavorite)
                                    }
                                }
                            },
                        )
                    }
                }
                Spacer(Modifier.height(MonkeyTheme.spacing.large))
            }
        }
    }
}

@Composable
private fun LazyGridLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    StaggeredVerticalGrid(
        maxColumnWidth = 220.dp,
        modifier = modifier
    ){
        content()
    }

/*    LazyVerticalGrid(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsWithImePadding(),
        verticalArrangement = Arrangement.Top,
        horizontalArrangement = Arrangement.Center,
        cells = GridCells.Fixed(2)
    ) {
        content()

        items(2) {
            Spacer(
                Modifier
                    .height(
                        MonkeyTheme.spacing.largeMedium
                    )
            )
        }
    }*/
}

@Composable
fun StaggeredVerticalGrid(
    modifier: Modifier = Modifier,
    maxColumnWidth: Dp,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        check(constraints.hasBoundedWidth) {
            "Unbounded width not supported"
        }
        val columns = ceil(constraints.maxWidth / maxColumnWidth.toPx()).toInt()
        val columnWidth = constraints.maxWidth / columns
        val itemConstraints = constraints.copy(maxWidth = columnWidth)
        val colHeights = IntArray(columns) { 0 } // track each column's height
        val placeables = measurables.map { measurable ->
            val column = shortestColumn(colHeights)
            val placeable = measurable.measure(itemConstraints)
            colHeights[column] += placeable.height
            placeable
        }

        val height = colHeights.maxOrNull()?.coerceIn(constraints.minHeight, constraints.maxHeight)
            ?: constraints.minHeight
        layout(
            width = constraints.maxWidth,
            height = height
        ) {
            val colY = IntArray(columns) { 0 }
            placeables.forEach { placeable ->
                val column = shortestColumn(colY)
                placeable.place(
                    x = columnWidth * column,
                    y = colY[column]
                )
                colY[column] += placeable.height
            }
        }
    }
}

private fun shortestColumn(colHeights: IntArray): Int {
    var minHeight = Int.MAX_VALUE
    var column = 0
    colHeights.forEachIndexed { index, height ->
        if (height < minHeight) {
            minHeight = height
            column = index
        }
    }
    return column
}