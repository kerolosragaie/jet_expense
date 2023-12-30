package hoods.com.jetexpense.presentation.income.viewmodel

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
    private val _incomeUiState: MutableStateFlow<IncomeUiState> by lazy { MutableStateFlow(IncomeUiState()) }
    val incomeUiState: StateFlow<IncomeUiState> by lazy { _incomeUiState }

    init {
        getAllIncome()
    }

    fun getAllIncome() = viewModelScope.launch {
        if (expenseRepo.income is ResultState.Success) {
            expenseRepo.income.data?.collectLatest {
                _incomeUiState.value = _incomeUiState.value.copy(
                    incomesList = it
                )
            }
        }
    }

    fun deleteIncome(id:Int)=viewModelScope.launch {
        expenseRepo.deleteIncome(id)
    }

}

