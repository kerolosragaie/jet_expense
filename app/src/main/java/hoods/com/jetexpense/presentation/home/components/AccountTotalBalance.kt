package hoods.com.jetexpense.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.core.utils.formatAmount

@Composable
fun AccountTotalBalance(
    totalIncome: Float,
    totalExpense: Float,
) {
    val balance = totalIncome - totalExpense
    Column {
        Row {
            Text(text = "Your total balance:")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "$" + formatAmount(balance),
                fontWeight = FontWeight.Bold,
            )
        }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            AccountCardItem(
                modifier = Modifier.weight(1f),
                cardType = AmountCardType.INCOME,
                amount = "$" + formatAmount(totalIncome),
            )
            Spacer(modifier = Modifier.width(5.dp))
            AccountCardItem(
                modifier = Modifier.weight(1f),
                cardType = AmountCardType.EXPENSE,
                amount = "-$" + formatAmount(totalExpense),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevAccountTotalBalance() {
    AccountTotalBalance(
        3000f,
        2000f,
    )

}