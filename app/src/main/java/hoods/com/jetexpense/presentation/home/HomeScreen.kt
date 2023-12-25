package hoods.com.jetexpense.presentation.home

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.data.dummy.dummyExpenseList
import hoods.com.jetexpense.data.dummy.dummyIncomeList
import hoods.com.jetexpense.presentation.home.components.AccountTotalBalance
import hoods.com.jetexpense.presentation.home.components.ExpenseCard
import hoods.com.jetexpense.presentation.home.components.IncomeCard
import hoods.com.jetexpense.presentation.home.viewmodel.HomeUiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
    onIncomeItemClick: (incomeId: Int) -> Unit,
    onSeeAllIncome: () -> Unit,
    onExpenseItemClick: (expenseId: Int) -> Unit,
    onSeeAllExpense: () -> Unit,
    onCLickInsert: () -> Unit,
) {

    Scaffold(
        modifier = modifier,
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AccountTotalBalance(
                    totalIncome = homeUiState.totalIncome,
                    totalExpense = homeUiState.totalExpense,
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                IncomeCard(
                    homeUiState = homeUiState,
                    onIncomeItemClick = onIncomeItemClick,
                    onClickSeeAll = onSeeAllIncome,
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ExpenseCard(
                    homeUiState = homeUiState,
                    onClickItem = onExpenseItemClick,
                    onClickSeeAll = onSeeAllExpense,
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                ElevatedButton(
                    onClick = onCLickInsert,
                ) {
                    Text(text = stringResource(R.string.insert).uppercase())
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}


@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true,
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
)
@Composable
fun PrevHomeScreen() {
    JetExpenseTheme {
        HomeScreen(
            homeUiState = HomeUiState(
                incomeList = dummyIncomeList,
                expenseList = dummyExpenseList,
                totalIncome = 5000f,
                totalExpense = 3000f,
            ),
            onIncomeItemClick = {},
            onSeeAllIncome = {},
            onExpenseItemClick = {},
            onSeeAllExpense = {},
            onCLickInsert = {},
        )
    }
}