package com.choice.recipes.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.choice.components.MonkeyIllustration
import com.choice.design.R
import com.choice.design.util.MonkeyScreen
import com.choice.recipes.ui.RecipesViewModel
import com.choice.recipes.ui.components.RecipeItem
import com.choice.recipes.ui.components.RecipeThrowable
import com.choice.recipes.util.FAILED_FAVORITE
import com.choice.recipes.util.RecipesEvent
import com.choice.recipes.util.SUCESS_FAVORITE
import com.choice.theme.MonkeyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LazyGridLayout(
    scaffoldState: ScaffoldState,
    menuViewModel: RecipesViewModel,
    mainController: NavController
) {
    val state = menuViewModel.state
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
            LazyGridLayout {
                itemsIndexed(state.recipeList.toList()) { _, item ->
                    RecipeItem(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                scope.launch {
                                    mainController.navigate(MonkeyScreen.RecipeDetail.route + "?recipeId=${item.id}")
                                }
                            },
                        item,
                        onFavoriteClick = {
                            scope.launch {
                                menuViewModel.onEvent(
                                    RecipesEvent.OnFavoriteChange(
                                        item.id,
                                        !item.favorite
                                    )
                                )
                                delay(500)
                                scaffoldState.snackbarHostState.showSnackbar(
                                    if (item.favorite) FAILED_FAVORITE else SUCESS_FAVORITE,
                                    if (item.favorite) "Add" else "Undo"
                                ).takeIf { result ->
                                    result == SnackbarResult.ActionPerformed
                                }?.apply {
                                    menuViewModel.onEvent(
                                        RecipesEvent.OnFavoriteChange(
                                            item.id,
                                            item.favorite
                                        )
                                    )
                                }
                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun LazyGridLayout(
    modifier: Modifier = Modifier,
    content: LazyGridScope.() -> Unit
) {

    LazyVerticalGrid(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalArrangement = Arrangement.Center,
        cells = GridCells.Fixed(2)
    ) {
        items(2) {
            Spacer(
                Modifier
                    .height(
                        MonkeyTheme.spacing.small
                    )
            )
        }

        content()

        items(2) {
            Spacer(
                Modifier
                    .height(
                        MonkeyTheme.spacing.largeMedium
                    )
            )
        }
    }
}
