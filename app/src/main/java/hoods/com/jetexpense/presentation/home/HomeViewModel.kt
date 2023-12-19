package hoods.com.jetexpense.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val incomeState = expenseRepo.income
    private val expenseState = expenseRepo.expense
    var homeState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            if (incomeState.data != null && expenseState.data != null) {
                combine(
                    incomeState.data,
                    expenseState.data
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
                        incomeState.message ?: "Something went wrong!"
                    )
                )
            }
        }
    }


}


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