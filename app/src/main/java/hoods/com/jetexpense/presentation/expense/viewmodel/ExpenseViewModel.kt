package hoods.com.jetexpense.presentation.expense.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.jetexpense.core.utils.ResultState
import hoods.com.jetexpense.domain.repo.ExpenseRepo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val expenseRepo: ExpenseRepo,
) : ViewModel() {
    var expenseUiState by mutableStateOf(ExpenseUiState())
        private set

    init {
        getAllExpense()
    }

    fun getAllExpense() = viewModelScope.launch {
        if (expenseRepo.expense is ResultState.Success) {
            expenseRepo.expense.data!!.collectLatest {
                expenseUiState = expenseUiState.copy(
                    expenseList = it
                )
            }
        }
    }

    fun deleteExpense(id: Int) = viewModelScope.launch {
        expenseRepo.deleteExpense(id)
    }
}