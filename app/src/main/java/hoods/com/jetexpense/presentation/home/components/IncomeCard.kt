package hoods.com.jetexpense.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetexpense.core.utils.Util
import hoods.com.jetexpense.core.utils.getColor
import hoods.com.jetexpense.data.dummy.dummyIncomeList
import hoods.com.jetexpense.presentation.home.viewmodel.HomeUiState

@Composable
fun IncomeCard(
    homeUiState: HomeUiState,
    onClickSeeAll: () -> Unit,
    onIncomeItemClick: (id: Int) -> Unit,
) {
    OverViewCard(
        title = "Income",
        amount = homeUiState.totalIncome,
        onClickSeeAll = onClickSeeAll,
        data = homeUiState.incomeList,
        values = { it.incomeAmount.toFloat() },
        colors = {
            getColor(
                it.incomeAmount.toFloat(),
                Util.incomeColor,
            )
        },
        row = { income ->
            IncomeRow(
                modifier = Modifier.clickable {
                    onIncomeItemClick.invoke(income.id)
                },
                name = income.title,
                description = income.description,
                amount = income.incomeAmount.toFloat(),
                color = getColor(
                    income.incomeAmount.toFloat(),
                    Util.incomeColor,
                ),
            )
        },
    )
}

@Composable
fun IncomeRow(
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
        negative = false,
    )

}


@Preview(showBackground = true)
@Composable
fun PrevIncomeCard() {
    IncomeCard(
        homeUiState = HomeUiState(
            incomeList = dummyIncomeList,
            totalIncome = 20000f,
        ),
        onClickSeeAll = {},
        onIncomeItemClick = { _ -> }
    )
}