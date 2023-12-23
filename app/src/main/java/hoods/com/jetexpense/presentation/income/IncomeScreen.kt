package hoods.com.jetexpense.presentation.income

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
import hoods.com.jetexpense.data.dummy.dummyIncomeList
import hoods.com.jetexpense.domain.models.Income
import hoods.com.jetexpense.presentation.home.components.IncomeRow
import hoods.com.jetexpense.core.components.TransactionStatement

@Composable
fun IncomeScreen(
    modifier: Modifier = Modifier,
    incomesList: List<Income>,
    onIncomeItemClick: (id: Int) -> Unit,
    onIncomeItemDelete: (id: Int) -> Unit,
) {
    TransactionStatement(
        modifier = modifier,
        items = incomesList,
        colors = { getColor(it.incomeAmount.toFloat(), Util.incomeColor) },
        amounts = { it.incomeAmount.toFloat() },
        amountsTotal = incomesList.sumOf { it.incomeAmount }.toFloat(),
        circleLabel = stringResource(R.string.receive),
        onItemSwiped = { item ->
            onIncomeItemDelete.invoke(item.id)
        },
        key = { it.id },
    ) {
        IncomeRow(
            modifier= Modifier.clickable { onIncomeItemClick.invoke(it.id)},
            name = it.title,
            description = "${stringResource(R.string.receive)}: ${formatDate(it.date, pattern = "dd/MM/YYY")}",
            amount = it.incomeAmount.toFloat(),
            color = getColor(it.incomeAmount.toFloat(), Util.incomeColor),
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun PrevIncomeScreen() {
    JetExpenseTheme {
        IncomeScreen(
            incomesList = dummyIncomeList,
            onIncomeItemClick = {

            },
            onIncomeItemDelete = {},
        )
    }
}