package hoods.com.jetexpense.core.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.utils.Constants.EXPENSE_SCREEN
import hoods.com.jetexpense.core.utils.Constants.HOME_SCREEN
import hoods.com.jetexpense.core.utils.Constants.INCOME_SCREEN
import hoods.com.jetexpense.core.utils.Constants.TRANSACTION_SCREEN

sealed class Screen {
    abstract val route: String
    abstract val iconResId: Int
    abstract val pageTitle: String

    object Home : Screen() {
        override val route: String
            get() = HOME_SCREEN
        override val iconResId: Int
            get() = R.drawable.ic_home
        override val pageTitle: String
            get() = "Home"
    }

    object Income : Screen() {
        override val route: String
            get() = INCOME_SCREEN
        override val iconResId: Int
            get() = R.drawable.ic_income_dollar
        override val pageTitle: String
            get() = "Income"

    }

    object Expense : Screen() {
        override val route: String
            get() = EXPENSE_SCREEN
        override val iconResId: Int
            get() = R.drawable.ic_expense_dollar
        override val pageTitle: String
            get() = "Expense"
    }

    object Transaction : Screen() {
        override val route: String
            get() = TRANSACTION_SCREEN
        override val iconResId: Int
            get() = R.drawable.add_entry
        override val pageTitle: String
            get() = "Transaction"

        const val transactionTypeArg = "Type"
        const val idTypeArg = "id"
        val arguments = listOf(
            navArgument(transactionTypeArg) {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(idTypeArg) {
                type = NavType.IntType
                defaultValue = -1
            }
        )
        val routeWithArgs =
            "$route?$transactionTypeArg={$transactionTypeArg}&$idTypeArg={$idTypeArg}"
    }
}

