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

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    expenseViewModel: ExpenseViewModel = hiltViewModel(),
    incomeViewModel: IncomeViewModel = hiltViewModel(),
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {

            AmountAlertDialog(homeViewModel = homeViewModel)

            HomeScreen(
                modifier = modifier,
                homeUiState = homeViewModel.homeUiState,
                onIncomeItemClick = {},
                onExpenseItemClick = {},
                onSeeAllIncome = {
                    navHostController.navigateToSingleTop(Screen.Income.route)
                },
                onSeeAllExpense = {
                    navHostController.navigateToSingleTop(Screen.Expense.route)
                },
                onCLickInsert = {
                    homeViewModel.showAmountAlertDialog.value = true
                },
            )
        }

        composable(Screen.Expense.route) {

            ExpenseScreen(
                modifier = modifier,
                expenseUiState = expenseViewModel.expenseUiState,
                onExpenseItemClick = { expenseId ->

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
                },
                onIncomeItemDelete = { incomeId ->
                    incomeViewModel.deleteIncome(incomeId)
                },
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

