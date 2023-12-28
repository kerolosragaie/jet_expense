package hoods.com.jetexpense.presentation.home.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.jetexpense.core.utils.ResultState
import hoods.com.jetexpense.domain.models.Expense
import hoods.com.jetexpense.domain.models.Income
import hoods.com.jetexpense.domain.repo.ExpenseRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _homeUiState: MutableStateFlow<HomeUiState> by lazy {
        MutableStateFlow(HomeUiState.Loading)
    }
    val homeUiState: StateFlow<HomeUiState> by lazy { _homeUiState }

    //To show/hide dialog
    val showAmountAlertDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        getIncomeAndExpense()
    }

    fun getIncomeAndExpense() = viewModelScope.launch {
        _homeUiState.emit(HomeUiState.Loading)
        if (income.data != null && expense.data != null) {
            combine(
                income.data, expense.data
            ) { incomeList: List<Income>, expenseList: List<Expense> ->
                HomeUiState.Success(
                    incomeList = incomeList,
                    expenseList = expenseList,
                    totalExpense = expenseList.sumOf { it.expenseAmount }.toFloat(),
                    totalIncome = incomeList.sumOf { it.incomeAmount }.toFloat(),
                )
            }.collectLatest { newHomeUiStates ->
                _homeUiState.emit(
                    newHomeUiStates
                )
            }
        } else {
            _homeUiState.emit(
                HomeUiState.Failure(
                    errorMessage = income.message ?: expense.message ?: "Something went wrong!"
                )
            )
        }
    }

    //Used with alert dialog only
    fun insertIncome(income: Income) = viewModelScope.launch {
        _homeUiState.emit(HomeUiState.Loading)
        when (expenseRepo.insertIncome(income)) {
            is ResultState.Failure -> {
                _homeUiState.emit(HomeUiState.Failure("Something went wrong!"))
            }

            is ResultState.Success -> {
                _homeUiState.emit(HomeUiState.Success())
            }
        }
    }

    //Used with alert dialog only
    fun insertExpense(expense: Expense) = viewModelScope.launch {
        _homeUiState.emit(HomeUiState.Loading)
        when (expenseRepo.insertExpense(expense)) {
            is ResultState.Failure -> {
                _homeUiState.emit(HomeUiState.Failure("Something went wrong!"))
            }

            is ResultState.Success -> {
                _homeUiState.emit(HomeUiState.Success())
            }
        }
    }
}


