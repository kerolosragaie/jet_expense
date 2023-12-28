package hoods.com.jetexpense.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import hoods.com.jetexpense.presentation.expense.ExpenseScreen
import hoods.com.jetexpense.presentation.expense.viewmodel.ExpenseViewModel
import hoods.com.jetexpense.presentation.home.HomeScreen
import hoods.com.jetexpense.presentation.home.components.AmountAlertDialog
import hoods.com.jetexpense.presentation.home.viewmodel.HomeViewModel
import hoods.com.jetexpense.presentation.income.IncomeScreen
import hoods.com.jetexpense.presentation.income.viewmodel.IncomeViewModel
import hoods.com.jetexpense.presentation.transaction.TransactionScreen
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionAssistedFactory

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    expenseViewModel: ExpenseViewModel = hiltViewModel(),
    incomeViewModel: IncomeViewModel = hiltViewModel(),
    transactionAssistedFactory: TransactionAssistedFactory,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {

            //AmountAlertDialog(homeViewModel = homeViewModel)

            HomeScreen(
                modifier = modifier,
                homeViewModel = homeViewModel,
                onIncomeItemClick = { incomeId ->
                    navHostController.navigateToTransactionScreen(
                        id = incomeId,
                        transactionType = Screen.Income.route,
                    )
                },
                onExpenseItemClick = { expenseId ->
                    navHostController.navigateToTransactionScreen(
                        id = expenseId,
                        transactionType = Screen.Expense.route,
                    )
                },
                onSeeAllIncome = {
                    navHostController.navigateToSingleTop(Screen.Income.route)
                },
                onSeeAllExpense = {
                    navHostController.navigateToSingleTop(Screen.Expense.route)
                },
                onCLickInsert = {
                    //homeViewModel.showAmountAlertDialog.value = true
                    navHostController.navigateToTransactionScreen()
                },
            )
        }

        composable(Screen.Expense.route) {

            ExpenseScreen(
                modifier = modifier,
                expenseUiState = expenseViewModel.expenseUiState,
                onExpenseItemClick = { expenseId ->
                    navHostController.navigateToTransactionScreen(
                        id = expenseId,
                        transactionType = Screen.Expense.route,
                    )
                },
                onExpenseItemDelete = { expenseId ->
                    expenseViewModel.deleteExpense(expenseId)
                },
            )
        }

        composable(Screen.Income.route) {
            IncomeScreen(
                modifier = modifier,
                incomeUiState = incomeViewModel.incomeUiState,
                onIncomeItemClick = { incomeId ->
                    navHostController.navigateToTransactionScreen(
                        id = incomeId,
                        transactionType = Screen.Income.route,
                    )
                },
                onIncomeItemDelete = { incomeId ->
                    incomeViewModel.deleteIncome(incomeId)
                },
            )
        }

        composable(
            route = Screen.Transaction.routeWithArgs,
            arguments = Screen.Transaction.arguments,
        ) { navBackStackEntry ->
            val transType =
                navBackStackEntry.arguments?.getString(Screen.Transaction.transactionTypeArg) ?: ""
            val transId = navBackStackEntry.arguments?.getInt(Screen.Transaction.idTypeArg) ?: -1

            TransactionScreen(
                modifier = modifier,
                transactionId = transId,
                transactionType = transType,
                assistedFactory = transactionAssistedFactory,
                navigateUp = {
                    homeViewModel.getIncomeAndExpense()
                    navHostController.navigateUp()
                }
            )

        }
    }

}


fun NavHostController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavHostController.navigateToTransactionScreen(
    id: Int = -1,
    transactionType: String = ""
) {
    val route =
        "${Screen.Transaction.route}?${Screen.Transaction.transactionTypeArg}=$transactionType&${Screen.Transaction.idTypeArg}=$id"

    navigateToSingleTop(route)
}

