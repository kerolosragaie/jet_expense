package hoods.com.jetexpense.presentation.expense.viewmodel

import hoods.com.jetexpense.domain.models.Expense

data class ExpenseUiState(
    val expenseList: List<Expense> = emptyList(),
)
