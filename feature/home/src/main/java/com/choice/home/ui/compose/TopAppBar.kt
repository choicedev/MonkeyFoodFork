package com.choice.home.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.East
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.choice.components.MonkeyCenterTopAppBarCustom
import com.choice.components.MonkeyIconButton
import com.choice.compose.MonkeyRow
import com.choice.compose.MonkeySurface
import com.choice.theme.MonkeyTheme
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun HomeTopAppBar() {

    var visible by remember {
        mutableStateOf(true)
    }
    val density = LocalDensity.current

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            with(density) { -40.dp.roundToPx() }
        } + fadeIn(),
        exit = shrinkVertically {
            with(density) { 40.dp.roundToPx() }
        } + fadeOut()
    ) {
        MonkeyCenterTopAppBarCustom(
            title = "MonkeyFood",
            textColor = MonkeyTheme.colors.primaryVariant,
            actions = {
                MonkeyIconButton(icon = Icons.Filled.Search) {
                    visible = false
                }
            }
        )
    }

    SearchToolBar(
        visible = visible,
        onClick = {
            visible = true
        }
    )

}