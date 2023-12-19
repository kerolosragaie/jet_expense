package hoods.com.jetexpense.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hoods.com.jetexpense.core.utils.ResultState
import hoods.com.jetexpense.data.dummy.dummyExpenseList
import hoods.com.jetexpense.data.dummy.dummyIncomeList
import hoods.com.jetexpense.domain.models.Expense
import hoods.com.jetexpense.domain.models.Income
import hoods.com.jetexpense.domain.repo.ExpenseRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val expenseRepo: ExpenseRepo,
) : ViewModel() {
    private val income = expenseRepo.income
    private val expense = expenseRepo.expense
    var homeState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
        private set
    var incomeState: MutableStateFlow<IncomeUiState> = MutableStateFlow(IncomeUiState.Loading)
        private set
    var expenseState: MutableStateFlow<ExpenseUiState> = MutableStateFlow(ExpenseUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            if (income.data != null && expense.data != null) {
                combine(
                    income.data,
                    expense.data
                ) { incomeList: List<Income>, expenseList: List<Expense>
                    ->
                    homeState.emit(
                        HomeUiState.Success(
                            incomeList = incomeList,
                            expenseList = expenseList,
                            totalExpense = expenseList
                                .sumOf { it.expenseAmount }.toFloat(),
                            totalIncome = incomeList
                                .sumOf { it.incomeAmount }.toFloat(),
                        )
                    )
                }
            } else {
                homeState.emit(
                    HomeUiState.Error(
                        income.message ?: "Something went wrong!"
                    )
                )
            }
        }
    }

    fun insertIncome() = viewModelScope.launch {
        incomeState.emit(IncomeUiState.Loading)
        when (val result = expenseRepo.insertIncome(dummyIncomeList.random())) {
            is ResultState.Failure -> incomeState.emit(
                IncomeUiState.Error(result.message ?: "Something went wrong!")
            )

            is ResultState.Success -> incomeState.emit(
                IncomeUiState.Success<Unit>(message = "Date inserted successfully.")
            )
        }
    }

    fun insertExpense() = viewModelScope.launch {
        expenseState.emit(ExpenseUiState.Loading)
        when (val result = expenseRepo.insertExpense(dummyExpenseList.random())) {
            is ResultState.Failure -> expenseState.emit(
                ExpenseUiState.Error(result.message ?: "Something went wrong!")
            )

            is ResultState.Success -> expenseState.emit(
                ExpenseUiState.Success<Unit>(message = "Date inserted successfully.")
            )
        }

    }
}


