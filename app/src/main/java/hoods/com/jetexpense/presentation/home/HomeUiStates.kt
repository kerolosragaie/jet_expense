package hoods.com.jetexpense.presentation.home

import hoods.com.jetexpense.domain.models.Expense
import hoods.com.jetexpense.domain.models.Income

sealed class HomeUiState {
    object Loading : HomeUiState()

    data class Success(
        val incomeList: List<Income>,
        val expenseList: List<Expense>,
        val totalExpense: Float = 0f,
        val totalIncome: Float = 0f,
    ) : HomeUiState()

    data class Error(val errorMessage: String) : HomeUiState()
}

sealed class IncomeUiState {
    object Loading : IncomeUiState()

    data class Success<T>(
        val data: T? = null,
        val message: String? = null,
    ) : IncomeUiState()

    data class Error(val errorMessage: String) : IncomeUiState()
}

sealed class ExpenseUiState {
    object Loading : ExpenseUiState()

    data class Success<T>(
        val data: T? = null,
        val message: String? = null,
    ) : ExpenseUiState()

    data class Error(val errorMessage: String) : ExpenseUiState()
}