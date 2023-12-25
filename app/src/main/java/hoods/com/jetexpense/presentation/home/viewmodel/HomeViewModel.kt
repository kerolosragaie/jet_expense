package hoods.com.jetexpense.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.jetexpense.domain.models.Expense
import hoods.com.jetexpense.domain.models.Income
import hoods.com.jetexpense.domain.repo.ExpenseRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val expenseRepo: ExpenseRepo,
) : ViewModel() {
    private val income = expenseRepo.income
    private val expense = expenseRepo.expense
    var homeUiState by mutableStateOf(HomeUiState())
        private set

    val showAmountAlertDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        getIncomeAndExpense()
    }

    fun getIncomeAndExpense() = viewModelScope.launch {
        if (income.data != null && expense.data != null) {
            combine(
                income.data,
                expense.data
            ) { incomeList: List<Income>, expenseList: List<Expense>
                ->
                homeUiState.copy(
                    incomeList = incomeList,
                    expenseList = expenseList,
                    totalExpense = expenseList
                        .sumOf { it.expenseAmount }.toFloat(),
                    totalIncome = incomeList
                        .sumOf { it.incomeAmount }.toFloat(),
                )
            }.collectLatest { newHomeUiState ->
                homeUiState = newHomeUiState
            }
        }
    }

    fun insertIncome(income: Income) = viewModelScope.launch {
        expenseRepo.insertIncome(income)
    }

    fun insertExpense(expense: Expense) = viewModelScope.launch {
        expenseRepo.insertExpense(expense)
    }
}


