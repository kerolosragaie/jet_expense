package hoods.com.jetexpense.presentation.transaction.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class TransactionViewModel @AssistedInject constructor(
    private val expenseRepo: ExpenseRepo,
    @Assisted private val transactionId: Int,
    @Assisted private val transactionType: String,
) : ViewModel(), TransactionCallBack {

    var state by mutableStateOf(TransactionState())
        private set
    val income: Income
        get() = state.run {
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
        get() = state.run {
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
        get() = state.title.isNotEmpty() &&
                state.description.isNotEmpty() &&
                state.amount.isNotEmpty()

    companion object {
        const val TAG = "transaction"
    }
    init {
        state = if (transactionId != -1) {
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
            state.copy(isUpdatingTransaction = true)
        } else {
            state.copy(isUpdatingTransaction = false)
        }
    }

    override fun onTitleChange(newValue: String) {
        state = state.copy(
            title = newValue
        )
    }

    override fun onAmountChange(newValue: String) {
        state = state.copy(
            amount = newValue
        )
    }

    override fun onDescriptionChange(newValue: String) {
        state = state.copy(
            description = newValue
        )
    }

    override fun onTransactionTypeChange(newValue: String) {
        state = state.copy(
            title = newValue
        )
    }

    override fun onDateChange(newValue: Long?) {
        newValue?.let {
            state = state.copy(
                date = Date(it)
            )
        }
    }

    override fun onScreenTypeChange(newValue: Screen) {
        state = state.copy(
            transactionScreen = newValue
        )
    }

    override fun onOpenDialog(newValue: Boolean) {
        state = state.copy(
            openDialog = newValue
        )
    }

    override fun onCategoryChange(newValue: Category) {
        state = state.copy(
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
            expenseRepo.getIncomeById(id).data!!.collectLatest {
                state = state.copy(
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
            expenseRepo.getExpenseById(id).data!!.collectLatest {
                state = state.copy(
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

data class TransactionState(
    val id: Int = 0,
    val title: String = "",
    val amount: String = "",
    val category: Category = Category.CLOTHING,
    val date: Date = Date(),
    val description: String = "",
    val transactionScreen: Screen = Screen.Income,
    val openDialog: Boolean = true,
    val isUpdatingTransaction: Boolean = false,
)

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

