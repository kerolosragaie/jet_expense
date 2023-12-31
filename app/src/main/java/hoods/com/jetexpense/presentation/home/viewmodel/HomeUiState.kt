package hoods.com.jetexpense.presentation.home.viewmodel

import hoods.com.jetexpense.domain.models.Expense
import hoods.com.jetexpense.domain.models.Income

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val incomeList: List<Income> = emptyList(),
        val expenseList: List<Expense> = emptyList(),
        val totalExpense: Float = 0f,
        val totalIncome: Float = 0f,
    ) : HomeUiState()

    data class Failure(
        val errorMessage: String,
    ) : HomeUiState()
}