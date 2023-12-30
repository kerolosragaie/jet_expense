package hoods.com.jetexpense.presentation.expense.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.jetexpense.core.utils.ResultState
import hoods.com.jetexpense.domain.repo.ExpenseRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val expenseRepo: ExpenseRepo,
) : ViewModel() {
    private val _expenseUiState: MutableStateFlow<ExpenseUiState> by lazy { MutableStateFlow(ExpenseUiState()) }
    val expenseUiState: StateFlow<ExpenseUiState> by lazy { _expenseUiState }
    init {
        getAllExpense()
    }

    private fun getAllExpense() = viewModelScope.launch {
        if (expenseRepo.expense is ResultState.Success) {
            expenseRepo.expense.data?.collectLatest {
                _expenseUiState.value = _expenseUiState.value.copy(
                    expenseList = it
                )
            }
        }
    }

    fun deleteExpense(id: Int) = viewModelScope.launch {
        expenseRepo.deleteExpense(id)
    }
}

