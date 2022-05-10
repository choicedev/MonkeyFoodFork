package com.choice.favorites.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.choice.components.MonkeyIllustration
import com.choice.design.R
import com.choice.design.util.MonkeyScreen
import com.choice.favorites.ui.FavoriteViewModel
import com.choice.favorites.ui.components.FavoriteItem
import com.choice.theme.MonkeyTheme
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun LazyColumnFavorites(
    scaffoldState: ScaffoldState,
    mainController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val state = viewModel.state

    when {
        state.favoriteList.isEmpty() -> {
            MonkeyIllustration(
                text = "No recipe found",
                illustration = R.drawable.il_cooking,
                size = MonkeyTheme.spacing.exxxtraLarge
            )
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(bottom = MonkeyTheme.spacing.large),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MonkeyTheme.spacing.small)
            ){
                items(state.favoriteList.toList()){ item ->
                    FavoriteItem(item) {
                        mainController.navigate(MonkeyScreen.RecipeDetail.route + "?recipeId=${item.id}")
                    }
                }

                item{
                    Spacer(Modifier.height(MonkeyTheme.spacing.large))
                }
            }
        }
    }
}