package hoods.com.jetexpense.core.utils

import androidx.compose.ui.graphics.Color

object Util {
    val incomeColor = listOf(
        Color(0xFF37EFBA),
        Color(0xFF04B97F),
        Color(0xFF005D57),
        Color(0xFF29B82F),
        Color(0xFF008605)
    )
    val expenseColor = listOf(
        Color(0xFFFFD7D0),
        Color(0xFFFFDC78),
        Color(0xFFFFAC12),
        Color(0xFFFFAC12),
        Color(0xFFFF6951),
    )
}


fun getColor(amount: Float, colors: List<Color>): Color =
    when {
        amount < 500 -> {
            colors[0]
        }

        amount < 1000 -> {
            colors[1]
        }

        amount < 5000 -> {
            colors[2]
        }

        amount < 10000 -> {
            colors[3]
        }

        else -> {
            colors[4]
        }

    }

