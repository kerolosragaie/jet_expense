package hoods.com.jetexpense.presentation.transaction.viewmodel

import hoods.com.jetexpense.core.navigation.Screen
import hoods.com.jetexpense.core.utils.Category

interface TransactionCallBack {
    fun onTitleChange(newValue: String)
    fun onAmountChange(newValue: String)
    fun onDescriptionChange(newValue: String)
    fun onTransactionTypeChange(newValue: String)
    fun onDateChange(newValue: Long?)
    fun onScreenTypeChange(newValue: Screen)
    fun onOpenDialog(newValue: Boolean)
    fun onCategoryChange(newValue: Category)
    fun addIncome()
    fun addExpense()
    fun getIncome(id: Int)
    fun getExpense(id: Int)
    fun updateIncome()
    fun updateExpense()
}