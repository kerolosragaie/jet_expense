package hoods.com.jetexpense.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import hoods.com.jetexpense.presentation.expense.ExpenseScreen
import hoods.com.jetexpense.presentation.home.HomeScreen
import hoods.com.jetexpense.presentation.home.components.AmountAlertDialog
import hoods.com.jetexpense.presentation.income.IncomeScreen
import hoods.com.jetexpense.presentation.transaction.TransactionScreen
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionAssistedFactory

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    transactionAssistedFactory: TransactionAssistedFactory,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {

            AmountAlertDialog()

            HomeScreen(
                modifier = modifier,
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
            )
        }

        composable(Screen.Expense.route) {
            ExpenseScreen(
                modifier = modifier,
                onExpenseItemClick = { expenseId ->
                    navHostController.navigateToTransactionScreen(
                        id = expenseId,
                        transactionType = Screen.Expense.route,
                    )
                },
            )
        }

        composable(Screen.Income.route) {
            IncomeScreen(
                modifier = modifier,
                onIncomeItemClick = { incomeId ->
                    navHostController.navigateToTransactionScreen(
                        id = incomeId,
                        transactionType = Screen.Income.route,
                    )
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

