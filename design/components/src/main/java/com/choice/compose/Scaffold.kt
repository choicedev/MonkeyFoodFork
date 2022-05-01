package com.choice.compose

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.choice.components.MonkeyBackgroundUI

@Composable
fun MonkeyScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDocked: Boolean = false,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    drawerGesturesEnabled: Boolean = true,
    drawerShape: Shape = MaterialTheme.shapes.large,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color = MaterialTheme.colors.surface,
    drawerContentColor: Color = contentColorFor(drawerBackgroundColor),
    drawerScrimColor: Color = DrawerDefaults.scrimColor,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier,
        scaffoldState,
        topBar,
        bottomBar,
        snackbarHost = {
            SnackbarHost(it) { data ->
                MonkeyBar(data)
            }
        },
        floatingActionButton,
        floatingActionButtonPosition,
        isFloatingActionButtonDocked,
        drawerContent,
        drawerGesturesEnabled,
        drawerShape,
        drawerElevation,
        drawerBackgroundColor,
        drawerContentColor,
        drawerScrimColor,
        backgroundColor,
        contentColor
    ){
        MonkeyBackgroundUI(
            content = { content(it) }
        )
    }
}