package hoods.com.jetexpense.presentation.transaction.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import hoods.com.jetexpense.core.navigation.Screen
import hoods.com.jetexpense.core.utils.Category
import hoods.com.jetexpense.core.utils.DateFormatter.formatDate
import hoods.com.jetexpense.domain.models.Expense
import hoods.com.jetexpense.domain.models.Income
import hoods.com.jetexpense.domain.repo.ExpenseRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class TransactionViewModel @AssistedInject constructor(
    private val expenseRepo: ExpenseRepo,
    @Assisted private val transactionId: Int,
    @Assisted private val transactionType: String,
) : ViewModel(), TransactionCallBack {

    private val _state: MutableStateFlow<TransactionUiState> by lazy {
        MutableStateFlow(
            TransactionUiState()
        )
    }
    val state: StateFlow<TransactionUiState> by lazy { _state }
    val income: Income
        get() = _state.value.run {
            Income(
                id = id,
                title = title,
                description = description,
                incomeAmount = amount.toDouble(),
                entryDate = formatDate(date),
                date = date,
            )
        }

    val expense: Expense
        get() = _state.value.run {
            Expense(
                id = id,
                title = title,
                description = description,
                expenseAmount = amount.toDouble(),
                entryDate = formatDate(date),
                date = date,
                category = category.title,
            )
        }
    override val isFieldsNotEmpty: Boolean
        get() = _state.value.title.isNotEmpty() &&
                _state.value.description.isNotEmpty() &&
                _state.value.amount.isNotEmpty()

    companion object {
        const val TAG: String = "transaction"
    }

    init {
        _state.value = if (transactionId != -1) {
            when (transactionType) {
                Screen.Income.route -> {
                    getIncome(transactionId)
                }

                Screen.Expense.route -> {
                    getExpense(transactionId)
                }

                else -> {
                    Log.i(TAG, "no route passed: $transactionType")
                }
            }
             _state.value.copy(isUpdatingTransaction = true)
        } else {
            //As the default is => isUpdatingTransaction = false
            _state.value
        }
    }

    override fun onTitleChange(newValue: String) {
        _state.value = _state.value.copy(
            title = newValue
        )
    }

    override fun onAmountChange(newValue: String) {
        _state.value = _state.value.copy(
            amount = newValue
        )
    }

    override fun onDescriptionChange(newValue: String) {
        _state.value = _state.value.copy(
            description = newValue
        )
    }

    override fun onTransactionTypeChange(newValue: String) {
        _state.value = _state.value.copy(
            title = newValue
        )
    }

    override fun onDateChange(newValue: Long?) {
        newValue?.let {
            _state.value = _state.value.copy(
                date = Date(it)
            )
        }
    }

    override fun onScreenTypeChange(newValue: Screen) {
        _state.value = _state.value.copy(
            transactionScreen = newValue
        )
    }

    override fun onOpenDialog(newValue: Boolean) {
        _state.value = _state.value.copy(
            openDialog = newValue
        )
    }

    override fun onCategoryChange(newValue: Category) {
        _state.value = _state.value.copy(
            category = newValue
        )
    }

    override fun addIncome() {
        viewModelScope.launch {
            expenseRepo.insertIncome(income)
        }
    }

    override fun addExpense() {
        viewModelScope.launch {
            expenseRepo.insertExpense(expense)
        }
    }

    override fun getIncome(id: Int) {
        viewModelScope.launch {
            expenseRepo.getIncomeById(id).collectLatest {
                _state.value = _state.value.copy(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    amount = it.incomeAmount.toString(),
                    transactionScreen = Screen.Income,
                    date = it.date,
                )
            }
        }
    }

    override fun getExpense(id: Int) {
        viewModelScope.launch {
            expenseRepo.getExpenseById(id).collectLatest {
                _state.value = _state.value.copy(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    amount = it.expenseAmount.toString(),
                    transactionScreen = Screen.Expense,
                    date = it.date,
                    category = Category.values()
                        .find { category ->
                            category.title == it.category
                        }
                        ?: Category.CLOTHING
                )
            }
        }
    }

    override fun updateIncome() {
        viewModelScope.launch {
            expenseRepo.updateIncome(income)
        }
    }

    override fun updateExpense() {
        viewModelScope.launch {
            expenseRepo.updateExpense(expense)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class TransactionViewModelFactory(
    private val assistedFactory: TransactionAssistedFactory,
    private val id: Int,
    private val transactionType: String?,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(id, transactionType) as T
    }
}

@AssistedFactory
interface TransactionAssistedFactory {
    fun create(id: Int, transactionType: String?): TransactionViewModel
}

