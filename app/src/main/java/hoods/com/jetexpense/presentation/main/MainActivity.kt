package hoods.com.jetexpense.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.components.CustomBottomBar
import hoods.com.jetexpense.core.components.ExpenseAppBar
import hoods.com.jetexpense.core.navigation.NavigationGraph
import hoods.com.jetexpense.core.navigation.Screen
import hoods.com.jetexpense.core.navigation.navigateToSingleTop
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.presentation.home.HomeScreen
import hoods.com.jetexpense.presentation.home.viewmodel.HomeViewModel
import hoods.com.jetexpense.presentation.main.viewmodel.MainViewModel
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionAssistedFactory
import javax.inject.Inject

enum class Theme {
    SYSTEM,
    DARK,
    LIGHT,
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var transactionAssistedFactory: TransactionAssistedFactory
    private val allScreens = listOf(Screen.Home, Screen.Income, Screen.Expense)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { JetExpenseApp() }
    }

    @Composable
    private fun JetExpenseApp() {
        val navHostController = rememberNavController()
        val viewModel: MainViewModel = hiltViewModel()
        val icon = when (viewModel.currentTheme) {
            Theme.DARK -> R.drawable.ic_switch_on
            else -> R.drawable.ic_switch_off
        }
        val currentScreen = rememberCurrentScreen(navHostController)

        CompositionLocalProvider(localAppTheme provides viewModel.currentTheme) {
            JetExpenseTheme(viewModel.currentTheme == Theme.DARK) {
                Scaffold(
                    topBar = {
                        ExpenseAppBar(
                            title = currentScreen.pageTitle,
                            icon = icon,
                            onSwitchClick = {
                                viewModel.changeTheme()
                            },
                            onNavigateUp = {
                                navHostController.navigateUp()
                            },
                        )
                    },
                    bottomBar = {
                        CustomBottomBar(
                            allScreens = allScreens,
                            onTabSelected = {
                                navHostController.navigateToSingleTop(it.route)
                            },
                            selectedTab = currentScreen,
                            onFabClicked = {
                                navHostController.navigateToSingleTop(Screen.Transaction.routeWithArgs)
                            },
                        )
                    },
                    floatingActionButtonPosition = FabPosition.End,
                ) { paddingValues ->
                    NavigationGraph(
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(4.dp),
                        navHostController = navHostController,
                        transactionAssistedFactory = transactionAssistedFactory,
                    )
                }
            }
        }


    }

    @Composable
    private fun rememberCurrentScreen(navController: NavController): Screen {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentScreen = currentBackStackEntry?.destination

        return allScreens.find { it.route == currentScreen?.route } ?: Screen.Transaction
    }

    private val localAppTheme = staticCompositionLocalOf<Theme> {
        error("No theme provided")
    }


}

@Preview(showSystemUi = true)
@Composable
fun AppPreview() {
    val homeViewModel: HomeViewModel = hiltViewModel()

    JetExpenseTheme {
        HomeScreen(
            homeViewModel = homeViewModel,
            onIncomeItemClick = {},
            onSeeAllIncome = {},
            onExpenseItemClick = {},
            onSeeAllExpense = {},
            onCLickInsert = {},
        )
    }
}