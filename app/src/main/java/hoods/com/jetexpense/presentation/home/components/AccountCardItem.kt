package hoods.com.jetexpense.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.core.utils.Util

enum class AmountCardType {
    EXPENSE,
    INCOME,
}

@Composable
fun AccountCardItem(
    modifier: Modifier = Modifier,
    cardType: AmountCardType,
    amount: String,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterVertically,
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val iconColor = when (cardType) {
                AmountCardType.EXPENSE -> Util.expenseColor.last()
                AmountCardType.INCOME -> Util.incomeColor.last()
            }
            AccountIconItem(
                modifier = Modifier.align(Alignment.End),
                icon = when (cardType) {
                    AmountCardType.EXPENSE -> Icons.Default.ArrowDownward
                    AmountCardType.INCOME -> Icons.Default.ArrowUpward
                },
                color = iconColor,
            )
            Text(
                text = when (cardType) {
                    AmountCardType.EXPENSE -> "Total expense".uppercase()
                    AmountCardType.INCOME -> "Total income".uppercase()
                },
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .8f),
            )
            Text(
                text = amount,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun AccountIconItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    color: Color,
) {
    Surface(
        modifier = modifier.size(36.dp),
        shape = CircleShape,
        color = color.copy(alpha = .1f),
        contentColor = color,
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                modifier = Modifier.padding(4.dp),
                imageVector = icon,
                contentDescription = null,
            )
        }
    }
}


@Preview
@Composable
fun PrevAccountCardItem() {
    AccountCardItem(
        cardType = AmountCardType.EXPENSE,
        amount = "33,000",
    )
}