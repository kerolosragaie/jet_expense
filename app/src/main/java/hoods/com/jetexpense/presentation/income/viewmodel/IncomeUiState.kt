package hoods.com.jetexpense.presentation.income.viewmodel

import hoods.com.jetexpense.domain.models.Income

data class IncomeUiState(
    val incomesList: List<Income> = emptyList(),
)