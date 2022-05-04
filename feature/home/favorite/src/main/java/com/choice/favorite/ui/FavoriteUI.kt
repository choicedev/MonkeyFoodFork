package com.choice.favorite.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.choice.compose.MonkeyColumn
import com.choice.compose.MonkeyDefaultColumn
import com.choice.compose.MonkeyScaffold
import androidx.compose.material.Text

@Composable
fun FavoriteUI(navController: NavController, mainNavController: NavController, scaffoldState: ScaffoldState) {


    MonkeyScaffold {
        MonkeyColumn {
            Text("aaaaaaaaa")
        }
    }
}