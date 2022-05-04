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
fun MonkeyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
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
    colors: TextFieldColors = monkeyTextFieldColors()
) {
    MonkeyColumn {
        TextField(
            value,
            onValueChange,
            modifier.fillMaxWidth(),
            enabled,
            readOnly,
            textStyle = MonkeyTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Normal
            ),
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