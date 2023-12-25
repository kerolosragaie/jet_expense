package hoods.com.jetexpense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hoods.com.jetexpense.core.navigation.NavigationGraph
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.data.dummy.dummyExpenseList
import hoods.com.jetexpense.data.dummy.dummyIncomeList
import hoods.com.jetexpense.presentation.home.HomeScreen
import hoods.com.jetexpense.presentation.home.viewmodel.HomeUiState
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionAssistedFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var transactionAssistedFactory: TransactionAssistedFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            JetExpenseTheme {
                NavigationGraph(
                    modifier = Modifier.padding(10.dp),
                    navHostController = navHostController,
                    transactionAssistedFactory = transactionAssistedFactory,
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