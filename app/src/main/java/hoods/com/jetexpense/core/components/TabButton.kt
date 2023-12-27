package hoods.com.jetexpense.core.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.theme.JetExpenseTheme


private val TabHeight = 56.dp
private const val InactiveTabOpacity = 0.60f
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100


@Composable
fun TabButton(
    text: String,
    @DrawableRes icon: Int,
    onSelected: () -> Unit,
    selected: Boolean,
) {
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val duration = if (selected) TabFadeInAnimationDelay else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = duration,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) onSurfaceColor else onSurfaceColor.copy(
            alpha = InactiveTabOpacity
        ),
        animationSpec = animSpec,
        label = "",
    )

    Row(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize()
            .height(TabHeight)
            .selectable(
                selected = selected,
                onClick = onSelected,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified,
                )
            )
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            tint = tabTintColor,
        )
        if (selected) {
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text.uppercase(),
                color = tabTintColor,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevTabTintColor() {
    JetExpenseTheme {
        TabButton(
            text = "Home",
            icon = R.drawable.bank,
            onSelected = {

            },
            selected = true,
        )
    }
}