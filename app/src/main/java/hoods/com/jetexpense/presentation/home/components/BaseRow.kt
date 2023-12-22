package hoods.com.jetexpense.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.core.utils.formatAmount
import hoods.com.jetexpense.data.dummy.dummyIncomeList


@Composable
fun BaseRow(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    amount: Float,
    color: Color,
    negative: Boolean,
) {
    val dollarSign = if (negative) "-$" else "$"
    val formattedAmount = formatAmount(amount)
    val typography = MaterialTheme.typography

    Row(
        modifier = modifier
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        VerticalIndicator(
            Modifier,
            color,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = subTitle,
                style = typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = dollarSign,
                style = typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
            Text(
                text = formattedAmount,
                style = typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterVertically),
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 12.dp)
                .size(24.dp)
        )
    }

    Divider(
        color = MaterialTheme.colorScheme.background,
        thickness = 2.dp,
    )
}


@Composable
private fun VerticalIndicator(
    modifier: Modifier,
    color: Color,
) {
    Spacer(
        modifier = modifier
            .size(4.dp, 36.dp)
            .background(color)
    )
}

@Preview(showBackground = true)
@Composable
fun PrevBaseRow() {
    val fakeIncome = dummyIncomeList.random()
    BaseRow(
        title = fakeIncome.title,
        subTitle = fakeIncome.description,
        amount = fakeIncome.incomeAmount.toFloat(),
        color = Color.DarkGray,
        negative = true,
    )
}