package com.choice.favorites.ui

import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoriteUI(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    Text("FAVORITE")
}