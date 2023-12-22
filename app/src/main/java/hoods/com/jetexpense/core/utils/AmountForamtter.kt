package hoods.com.jetexpense.core.utils

import java.text.DecimalFormat


private val AmountDecimalFormat = DecimalFormat("#,###.##")

fun formatAmount(amount: Float): String = AmountDecimalFormat.format(amount)
