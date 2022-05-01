package com.choice.animation

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.choice.compose.MonkeyDefaultColumn
import com.choice.theme.MonkeyTheme

@Composable
fun shimmerColorShades() = listOf(
    MonkeyTheme.colors.primary.copy(0.8f),
    MonkeyTheme.colors.primary.copy(0.2f),
    MonkeyTheme.colors.primary.copy(0.8f)
)

@Composable
fun CustomShimmerAnimation(
    content: @Composable (Brush) -> Unit
) {

    /**
    Create InfiniteTransition
    which holds child animation like [Transition]
    animations start running as soon as they enter
    the composition and do not stop unless they are removed
     */
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        /**
        Specify animation positions,
        initial Values 0F means it starts from 0 position
         */
        initialValue = -50f,
        targetValue = 2100f,
        animationSpec = infiniteRepeatable(

            /**
             * Tween Animates between values over specified [durationMillis]
             */
            tween(durationMillis = 1800, easing = LinearEasing, delayMillis = 100),
            RepeatMode.Restart
        )
    )

    /**
     * Create a gradient using the list of colors
     * Use Linear Gradient for animating in any direction according to requirement
     * start=specifies the position to start with in cartesian like system
     *       Offset(10f,10f) means x(10,0) , y(0,10)
     * end= Animate the end position to give the shimmer effect using the transition created above
     */
    val brush = Brush.linearGradient(
        colors = shimmerColorShades(),
        start = Offset(0f, 0f),
        end = Offset(translateAnim, translateAnim)
    )

    content(brush)

}

@Composable
fun DefaultShimmerAnimation(
    cardSize: Int,
    textfield: Boolean = false,
) {

    /**
    Create InfiniteTransition
    which holds child animation like [Transition]
    animations start running as soon as they enter
    the composition and do not stop unless they are removed
     */
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        /**
        Specify animation positions,
        initial Values 0F means it starts from 0 position
         */
        initialValue = -50f,
        targetValue = 2100f,
        animationSpec = infiniteRepeatable(

            /**
             * Tween Animates between values over specified [durationMillis]
             */
            tween(durationMillis = 1800, easing = LinearEasing, delayMillis = 100),
            RepeatMode.Restart
        )
    )

    /**
     * Create a gradient using the list of colors
     * Use Linear Gradient for animating in any direction according to requirement
     * start=specifies the position to start with in cartesian like system
     *       Offset(10f,10f) means x(10,0) , y(0,10)
     * end= Animate the end position to give the shimmer effect using the transition created above
     */
    val brush = Brush.linearGradient(
        colors = shimmerColorShades(),
        start = Offset(0f, 0f),
        end = Offset(translateAnim, translateAnim)
    )

    ShimmerItem(brush = brush, cardSize, textfield)

}


@Composable
fun ShimmerItem(
    brush: Brush,
    cardSize: Int,
    textfield: Boolean = false,
) {

    /**
     * Column composable shaped like a rectangle,
     * set the [background]'s [brush] with the brush receiving from [ShimmerAnimation]
     * which will get animated.
     * Add few more Composable to test
     */
    MonkeyDefaultColumn(modifier = Modifier.padding(0.dp)) {
        if (textfield) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(vertical = 8.dp)
                    .background(
                        brush = brush,
                        shape = MonkeyTheme.shapes.medium
                    )
                    .clip(shape = MonkeyTheme.shapes.medium)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(cardSize.dp)
                .background(
                    brush = brush,
                    shape = MonkeyTheme.shapes.medium
                )
                .clip(shape = MonkeyTheme.shapes.medium)
        )
    }
}