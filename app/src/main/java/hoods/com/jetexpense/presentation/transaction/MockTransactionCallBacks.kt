package hoods.com.jetexpense.presentation.transaction

import hoods.com.jetexpense.core.navigation.Screen
import hoods.com.jetexpense.core.utils.Category
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionCallBack

class MockTransactionCallBacks(override val isFieldsNotEmpty: Boolean = false) :
    TransactionCallBack {
    override fun onTitleChange(newValue: String) {
    }

    override fun onAmountChange(newValue: String) {
    }

    override fun onDescriptionChange(newValue: String) {
    }

    override fun onTransactionTypeChange(newValue: String) {
    }

    override fun onDateChange(newValue: Long?) {
    }

    override fun onScreenTypeChange(newValue: Screen) {
    }

    override fun onOpenDialog(newValue: Boolean) {
    }

    override fun onCategoryChange(newValue: Category) {
    }

    override fun addIncome() {
    }

    override fun addExpense() {
    }

    override fun getIncome(id: Int) {
    }

    override fun getExpense(id: Int) {
    }

    override fun updateIncome() {
    }

    override fun updateExpense() {
    }
}