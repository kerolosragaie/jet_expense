package hoods.com.jetexpense.presentation.transaction.viewmodel

import hoods.com.jetexpense.core.navigation.Screen
import hoods.com.jetexpense.core.utils.Category
import java.util.Date

data class TransactionUiState(
    val id: Int = 0,
    val title: String = "",
    val amount: String = "",
    val category: Category = Category.CLOTHING,
    val date: Date = Date(),
    val description: String = "",
    val transactionScreen: Screen = Screen.Income,
    val openDialog: Boolean = true,
    val isUpdatingTransaction: Boolean = false,
)
