package hoods.com.jetexpense.presentation.income.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class IncomeViewModel @Inject constructor(
    private val expenseRepo: ExpenseRepo,
) : ViewModel() {

    var incomeUiState by mutableStateOf(IncomeUiState())
            private set


    init {
        getAllIncome()
    }

    fun getAllIncome() = viewModelScope.launch {
        if (expenseRepo.income is ResultState.Success) {
            expenseRepo.income.data!!.collectLatest {
                incomeUiState = incomeUiState.copy(
                    incomesList = it
                )
            }
        }
    }

    fun deleteIncome(id:Int)=viewModelScope.launch {
        expenseRepo.deleteIncome(id)
    }

}

