package hoods.com.jetexpense.presentation.expense

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.core.utils.DateFormatter.formatDate
import hoods.com.jetexpense.core.utils.Util
import hoods.com.jetexpense.core.utils.getColor
import hoods.com.jetexpense.core.components.TransactionStatement
import hoods.com.jetexpense.data.dummy.dummyExpenseList
import hoods.com.jetexpense.presentation.expense.viewmodel.ExpenseUiState
import hoods.com.jetexpense.presentation.home.components.ExpenseRow

@Composable
fun ExpenseScreen(
    modifier: Modifier = Modifier,
    expenseUiState: ExpenseUiState,
    onExpenseItemClick: (id: Int) -> Unit,
    onExpenseItemDelete: (id: Int) -> Unit,
) {
    TransactionStatement(
        modifier = modifier,
        items = expenseUiState.expenseList,
        colors = { getColor(it.expenseAmount.toFloat(), Util.expenseColor) },
        amounts = { it.expenseAmount.toFloat() },
        amountsTotal = expenseUiState.expenseList.sumOf { it.expenseAmount }.toFloat(),
        circleLabel = stringResource(R.string.pay),
        onItemSwiped = { item ->
            onExpenseItemDelete.invoke(item.id)
        },
        key = { it.id },
    ) {
        ExpenseRow(
            modifier = Modifier.clickable { onExpenseItemClick.invoke(it.id) },
            name = it.title,
            description = "${stringResource(R.string.receive)}: ${
                formatDate(
                    it.date,
                    pattern = "dd/MM/YYY"
                )
            }",
            amount = it.expenseAmount.toFloat(),
            color = getColor(it.expenseAmount.toFloat(), Util.expenseColor),
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun PrevExpenseScreen() {
    val expenseUiState = ExpenseUiState(dummyExpenseList)

    JetExpenseTheme {
        ExpenseScreen(
            expenseUiState = expenseUiState,
            onExpenseItemClick = {},
            onExpenseItemDelete = {},
        )
    }
}