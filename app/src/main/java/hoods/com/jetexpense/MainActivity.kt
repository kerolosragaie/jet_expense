package hoods.com.jetexpense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import hoods.com.jetexpense.presentation.home.components.AmountAlertDialog
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.data.dummy.dummyExpenseList
import hoods.com.jetexpense.data.dummy.dummyIncomeList
import hoods.com.jetexpense.presentation.home.HomeScreen
import hoods.com.jetexpense.presentation.home.viewmodel.HomeUiState
import hoods.com.jetexpense.presentation.home.viewmodel.HomeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val homeViewModel: HomeViewModel = hiltViewModel()
            var showAmountAlertDialog by remember { mutableStateOf(false) }

            JetExpenseTheme {
                AmountAlertDialog(
                    showDialog = showAmountAlertDialog,
                    onClickCancel = { showAmountAlertDialog = false },
                    onClickOk = { showAmountAlertDialog = false },
                )

                HomeScreen(
                    homeUiState = homeViewModel.homeUiState,
                    onIncomeItemClick = {},
                    onSeeAllIncome = {},
                    onExpenseItemClick = {},
                    onSeeAllExpense = {},
                    onCLickInsert = { showAmountAlertDialog = true },
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
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