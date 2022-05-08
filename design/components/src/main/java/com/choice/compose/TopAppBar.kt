package com.choice.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.choice.compose.MonkeyTextField
import com.choice.theme.MonkeyTheme
import com.choice.theme.compose.monkeyTextFieldColors
import kotlin.math.abs
import kotlin.math.max

@Composable
fun MonkeyCenterTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    textColor: Color = MonkeyTheme.colors.onSurface,
    backColor: Color = textColor,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MonkeyTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    onBack: (() -> Unit)? = null,
    elevation: Dp = 0.dp
) {
    CenterTopAppBar(
        modifier = modifier,
        title = {
            Text(
                modifier = Modifier.wrapContentHeight(),
                text = title,
                style = MonkeyTheme.typography.h6.copy(
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                ),
                color = textColor,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            onBack?.let {
                MonkeyIconButton(
                    icon = Icons.Filled.KeyboardBackspace,
                    color = backColor
                ) {
                    it()
                }
            }
        },
        actions = actions,
        contentColor = contentColor,
        backgroundColor = backgroundColor,
        elevation = elevation
    )
}

@Composable
fun MonkeyCenterSearchTopAppBar(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    modifier: Modifier = Modifier,
    textColor: Color = MonkeyTheme.colors.onSurface,
    backColor: Color = textColor,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MonkeyTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    navigateUp: (() -> Unit)? = null,
    elevation: Dp = 0.dp
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    CenterSearchTopAppBar(
        modifier = modifier,
        textfield = {
            MonkeyTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                placeholder = placeholder,
                enabled = true,
                imeAction = ImeAction.Search,
                onAction = KeyboardActions {
                    keyboardController?.hide()
                },
                maxLines = 1,
                singleLine = true,
            )
        },
        navigationIcon = {
            navigateUp?.let {
                MonkeyIconButton(
                    icon = Icons.Filled.KeyboardBackspace,
                    color = backColor
                ) {
                    it()
                }
            }
        },
        actions = actions,
        contentColor = contentColor,
        backgroundColor = backgroundColor,
        elevation = elevation
    )
}

val AppBarHeight = 56.dp
val AppBarHorizontalPadding = 4.dp
var iconWidth = 72.dp - AppBarHorizontalPadding
var withoutIconWidth = 72.dp - AppBarHorizontalPadding

@Composable
private fun CenterTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MonkeyTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    val defLeftSectionWidth = if (navigationIcon == null) withoutIconWidth else iconWidth
    var leftSectionWidth by remember { mutableStateOf(defLeftSectionWidth) }
    var rightSectionWidth by remember { mutableStateOf(-1f) }
    var rightSectionPadding by remember { mutableStateOf(0f) }

    MonkeyAppBar(
        backgroundColor,
        contentColor,
        elevation,
        AppBarDefaults.ContentPadding,
        RectangleShape,
        modifier
    ) {
        if (navigationIcon == null) {
            Spacer(Modifier.width(withoutIconWidth))
        } else {
            Row(
                Modifier
                    .fillMaxHeight()
                    .width(iconWidth),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high,
                    content = navigationIcon
                )
            }
        }

        Row(
            Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (leftSectionWidth != defLeftSectionWidth
                || rightSectionPadding != 0f
            ) {
                ProvideTextStyle(value = MonkeyTheme.typography.h6) {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                        content = title
                    )
                }
            }
        }

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            with(LocalDensity.current) {
                Row(
                    Modifier
                        .fillMaxHeight()
                        .padding(start = rightSectionPadding.toDp())
                        .onGloballyPositioned {
                            rightSectionWidth = it.size.width.toFloat()
                            if (leftSectionWidth == defLeftSectionWidth
                                && rightSectionWidth != -1f
                                && rightSectionPadding == 0f
                            ) {
                                /*
                                 Find the maximum width of the sections (left or right).
                                 As a result, both sections should have the same width.
                                 */
                                val maxWidth = max(
                                    leftSectionWidth.toPx(),
                                    rightSectionWidth
                                )
                                leftSectionWidth = maxWidth.toDp()
                                rightSectionPadding = abs(rightSectionWidth - maxWidth)
                            }
                        },
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    content = actions
                )
            }
        }
    }
}


var iconWidthSearch = 36.dp - AppBarHorizontalPadding
var withoutIconWidthSearch = AppBarHorizontalPadding
@Composable
private fun CenterSearchTopAppBar(
    textfield: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MonkeyTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    val defLeftSectionWidth = if (navigationIcon == null) withoutIconWidthSearch else AppBarHorizontalPadding
    var leftSectionWidth by remember { mutableStateOf(defLeftSectionWidth) }
    var rightSectionWidth by remember { mutableStateOf(-1f) }
    var rightSectionPadding by remember { mutableStateOf(0f) }

    MonkeyAppBarSearch(
        backgroundColor,
        contentColor,
        elevation,
        RectangleShape,
        modifier
    ) {

        Row(
            Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (leftSectionWidth != defLeftSectionWidth
                || rightSectionPadding != 0f
            ) {
                ProvideTextStyle(value = MonkeyTheme.typography.subtitle1) {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                        content = textfield
                    )
                }
            }
        }

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            with(LocalDensity.current) {
                Row(
                    Modifier
                        .fillMaxHeight()
                        .padding(start = rightSectionPadding.toDp())
                        .onGloballyPositioned {
                            rightSectionWidth = it.size.width.toFloat()
                            if (leftSectionWidth == defLeftSectionWidth
                                && rightSectionWidth != -1f
                                && rightSectionPadding == 0f
                            ) {
                                /*
                                 Find the maximum width of the sections (left or right).
                                 As a result, both sections should have the same width.
                                 */
                                val maxWidth = max(
                                    leftSectionWidth.toPx(),
                                    rightSectionWidth
                                )
                                leftSectionWidth = maxWidth.toDp()
                                rightSectionPadding = abs(rightSectionWidth - maxWidth)
                            }
                        },
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    content = actions
                )
            }
        }
    }
}

@Composable
private fun MonkeyAppBar(
    backgroundColor: Color,
    contentColor: Color,
    elevation: Dp,
    contentPadding: PaddingValues,
    shape: Shape,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = shape,
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .height(AppBarHeight),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

@Composable
private fun MonkeyAppBarSearch(
    backgroundColor: Color,
    contentColor: Color,
    elevation: Dp,
    shape: Shape,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = shape,
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(AppBarHeight),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}