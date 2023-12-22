package hoods.com.jetexpense.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.data.dummy.dummyExpenseList
import hoods.com.jetexpense.data.dummy.dummyIncomeList
import hoods.com.jetexpense.presentation.home.components.AccountTotalBalance
import hoods.com.jetexpense.presentation.home.components.IncomeCard
import hoods.com.jetexpense.presentation.home.viewmodel.HomeUiState

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    onIncomeItemClick: (id: Int) -> Unit,
    onSeeAllIncome: () -> Unit,
) {

    Scaffold { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding)
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
                    onClickItem = onIncomeItemClick,
                    onClickSeeAll = onSeeAllIncome,
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                //TODO replace with expense card
                IncomeCard(
                    homeUiState = homeUiState,
                    onClickItem = onIncomeItemClick,
                    onClickSeeAll = onSeeAllIncome,
                )
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
        )
    }
}