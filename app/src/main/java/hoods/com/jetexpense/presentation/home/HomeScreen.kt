package hoods.com.jetexpense.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.components.ErrorScreen
import hoods.com.jetexpense.core.components.LoadingScreen
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.presentation.home.components.AccountTotalBalance
import hoods.com.jetexpense.presentation.home.components.ExpenseCard
import hoods.com.jetexpense.presentation.home.components.IncomeCard
import hoods.com.jetexpense.presentation.home.viewmodel.HomeUiState
import hoods.com.jetexpense.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onIncomeItemClick: (incomeId: Int) -> Unit,
    onSeeAllIncome: () -> Unit,
    onExpenseItemClick: (expenseId: Int) -> Unit,
    onSeeAllExpense: () -> Unit,
) {
    val homeUiStates = homeViewModel.homeUiState.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = modifier,
    ) { contentPadding ->
        when (homeUiStates) {
            is HomeUiState.Failure -> {
                ErrorScreen(
                    message = homeUiStates.errorMessage,
                    retryFunc = {
                        homeViewModel.getIncomeAndExpense()
                    }
                )
            }

            is HomeUiState.Loading -> {
                LoadingScreen()
            }

            is HomeUiState.Success -> {
                HomeScreenBody(
                    contentPadding = contentPadding,
                    homeUiState = homeUiStates,
                    onIncomeItemClick = onIncomeItemClick,
                    onSeeAllIncome = onSeeAllIncome,
                    onExpenseItemClick = onExpenseItemClick,
                    onSeeAllExpense = onSeeAllExpense,
                    onCLickInsert = {
                        homeViewModel.showOrHildeAmountAlertDialog()
                    },
                )
            }
        }

    }
}


@Composable
private fun HomeScreenBody(
    contentPadding: PaddingValues,
    homeUiState: HomeUiState.Success,
    onIncomeItemClick: (incomeId: Int) -> Unit,
    onSeeAllIncome: () -> Unit,
    onExpenseItemClick: (expenseId: Int) -> Unit,
    onSeeAllExpense: () -> Unit,
    onCLickInsert: () -> Unit,
) {
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
    val homeViewModel: HomeViewModel = hiltViewModel()

    JetExpenseTheme {
        HomeScreen(
            homeViewModel = homeViewModel,
            onIncomeItemClick = {},
            onSeeAllIncome = {},
            onExpenseItemClick = {},
            onSeeAllExpense = {},
        )
    }
}