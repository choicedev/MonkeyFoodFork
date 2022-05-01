package com.choice.compose

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.choice.theme.MonkeyTheme
import com.choice.theme.compose.monkeyTextFieldColors


@Composable
fun MonkeyOutlinedTextFieldState(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    label: String,
    enabled: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textStyle: TextStyle = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Normal),
    maxLength: Int,
    singleLine: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    textError: String? = null
) {
    MonkeyOutlinedTextField(
        modifier = modifier,
        value = valueState.value,
        onValueChange = {
            valueState.value = it.take(maxLength)
        },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.subtitle2.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        },
        singleLine = singleLine,
        textStyle = textStyle,
        trailingIcon = trailingIcon,
        enabled = enabled,
        keyboardType = keyboardType,
        imeAction = imeAction,
        keyboardActions = onAction,
        visualTransformation = visualTransformation,
        isError = isError,
        textError = textError,
    )
}

@Composable
fun MonkeyOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    isError: Boolean = false,
    textError: String? = null,
) {

    MonkeyColumn(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.fillMaxWidth(),
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            interactionSource = interactionSource,
            shape = MonkeyTheme.shapes.medium,
            colors = colors
        )

        if(isError && textError != null){
            ErrorAnimation(textError, isError = isError)
        }
    }
}

@Composable
internal fun ErrorAnimation(
    value: String,
    isError: Boolean,
) {
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = isError,
        enter = slideInVertically(
            initialOffsetY = { with(density) { -1.dp.roundToPx() } },
        ) + expandVertically(
            expandFrom = Alignment.Bottom
        ) ,
        exit = slideOutVertically(
            targetOffsetY = {
                with(density) {
                    -1.dp.roundToPx()
                }
            },
            animationSpec = tween(700)
        ) + shrinkVertically(
            shrinkTowards = Alignment.Top
        )
    ) {
        MonkeyRow(
            Modifier
                .fillMaxWidth()
                .padding(start = MonkeyTheme.spacing.extraSmall)
                .padding(vertical = MonkeyTheme.spacing.extraSmall)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.caption.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onError
                ),
            )
        }
    }
}


@Composable
fun MonkeyTextFieldState(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    label: @Composable (() -> Unit)? = null,
    placeholder: String? = null,
    enabled: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textStyle: TextStyle = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Normal),
    maxLength: Int,
    singleLine: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    textError: String? = null,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors()
) {
    MonkeyTextField(
        modifier = modifier,
        value = valueState.value,
        onValueChange = {
            valueState.value = it.take(maxLength)
        },
        label = label,
        placeholder = placeholder,
        singleLine = singleLine,
        textStyle = textStyle,
        trailingIcon = trailingIcon,
        enabled = enabled,
        keyboardType = keyboardType,
        imeAction = imeAction,
        onAction = onAction,
        visualTransformation = visualTransformation,
        isError = isError,
        textError = textError,
        colors = colors,
    )
}

@Composable
fun MonkeyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    textError: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape =
        MonkeyTheme.shapes.medium,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors()
) {
    MonkeyColumn {
        TextField(
            value,
            onValueChange,
            modifier.fillMaxWidth(),
            enabled,
            readOnly,
            textStyle,
            label = label,
            placeholder = {
                placeholder?.let {
                    Text(
                        text = it,
                    )
                }
            },
            leadingIcon,
            trailingIcon,
            isError,
            visualTransformation,
            KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            onAction,
            singleLine,
            maxLines,
            interactionSource,
            shape,
            colors
        )
        if(isError && textError != null){
            ErrorAnimation(value = textError, isError = isError)
        }
    }
}