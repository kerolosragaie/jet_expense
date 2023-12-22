package hoods.com.jetexpense.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetexpense.core.utils.Util
import hoods.com.jetexpense.core.utils.getColor
import hoods.com.jetexpense.data.dummy.dummyExpenseList
import hoods.com.jetexpense.presentation.home.viewmodel.HomeUiState

@Composable
fun ExpenseCard(
    homeUiState: HomeUiState,
    onClickSeeAll: () -> Unit,
    onClickItem: (id: Int) -> Unit,
) {
    OverViewCard(
        title = "Expense",
        amount = homeUiState.totalExpense,
        onClickSeeAll = onClickSeeAll,
        data = homeUiState.expenseList,
        values = { it.expenseAmount.toFloat() },
        colors = {
            getColor(
                it.expenseAmount.toFloat(),
                Util.expenseColor,
            )
        },
        row = { expense ->
            ExpenseRow(
                modifier = Modifier.clickable {
                    onClickItem.invoke(expense.id)
                },
                name = expense.title,
                description = expense.description,
                amount = expense.expenseAmount.toFloat(),
                color = getColor(
                    expense.expenseAmount.toFloat(),
                    Util.expenseColor,
                ),
            )
        },
    )
}

@Composable
fun ExpenseRow(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    amount: Float,
    color: Color,
) {
    BaseRow(
        modifier = modifier,
        color = color,
        title = name,
        subTitle = description,
        amount = amount,
        negative = true,
    )

}


@Preview(showBackground = true)
@Composable
fun PrevExpenseCard() {
    ExpenseCard(
        homeUiState = HomeUiState(
            expenseList = dummyExpenseList,
            totalExpense = 20000f,
        ),
        onClickSeeAll = {},
        onClickItem = { _ -> }
    )
}