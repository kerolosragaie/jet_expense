package hoods.com.jetexpense.core.navigation

import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.utils.Constants.EXPENSE_SCREEN
import hoods.com.jetexpense.core.utils.Constants.HOME_SCREEN
import hoods.com.jetexpense.core.utils.Constants.INCOME_SCREEN

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
}

