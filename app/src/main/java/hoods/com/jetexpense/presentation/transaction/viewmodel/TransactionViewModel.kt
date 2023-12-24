package hoods.com.jetexpense.presentation.transaction.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.jetexpense.core.navigation.Screen
import hoods.com.jetexpense.core.utils.Category
import hoods.com.jetexpense.core.utils.DateFormatter.formatDate
import hoods.com.jetexpense.domain.models.Expense
import hoods.com.jetexpense.domain.models.Income
import hoods.com.jetexpense.domain.repo.ExpenseRepo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

enum class TransactionType {
    INCOME,
    EXPENSE,
}

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val expenseRepo: ExpenseRepo,
    @Assisted private val transactionId: Int,
    @Assisted private val transactionType: TransactionType,
) : ViewModel(), TransactionCallBack {

    var transactionState by mutableStateOf(TransactionState())
        private set
    val income: Income
        get() = transactionState.run {
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
        get() = transactionState.run {
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

    override fun onTitleChange(newValue: String) {
        transactionState = transactionState.copy(
            title = newValue
        )
    }

    override fun onAmountChange(newValue: String) {
        transactionState = transactionState.copy(
            amount = newValue
        )
    }

    override fun onDescriptionChange(newValue: String) {
        transactionState = transactionState.copy(
            description = newValue
        )
    }

    override fun onTransactionTypeChange(newValue: String) {
        transactionState = transactionState.copy(
            title = newValue
        )
    }

    override fun onDateChange(newValue: Long?) {
        newValue?.let {
            transactionState = transactionState.copy(
                date = Date(it)
            )
        }
    }

    override fun onScreenTypeChange(newValue: Screen) {
        transactionState = transactionState.copy(
            transactionScreen = newValue
        )
    }

    override fun onOpenDialog(newValue: Boolean) {
        transactionState = transactionState.copy(
            openDialog = newValue
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
                transactionState = transactionState.copy(
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
                transactionState = transactionState.copy(
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
private class TransactionViewModelFactory(
    private val assistedFactory: TransactionAssistedFactory,
    private val id: Int,
    private val transactionType: TransactionType?,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(id, transactionType) as T
    }
}

@AssistedFactory
interface TransactionAssistedFactory {
    fun create(id: Int, transactionType: TransactionType?): TransactionViewModel
}

