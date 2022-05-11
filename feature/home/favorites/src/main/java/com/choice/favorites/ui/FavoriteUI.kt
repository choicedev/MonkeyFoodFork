package com.choice.favorites.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.choice.compose.MonkeyCenterTopAppBar
import com.choice.compose.MonkeyColumn
import com.choice.compose.MonkeyScaffold
import com.choice.favorites.ui.compose.LazyColumnFavorites
import com.choice.theme.MonkeyTheme

@Composable
fun FavoriteUI(
    scaffoldState: ScaffoldState,
    mainController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {




    MonkeyScaffold(
        topBar = {
            MonkeyCenterTopAppBar(title = "Favorites")
        }
    ) {
        MonkeyColumn(
            modifier = Modifier.padding(horizontal = MonkeyTheme.spacing.small)
        ) {
            LazyColumnFavorites(scaffoldState, mainController)
        }
    }

}