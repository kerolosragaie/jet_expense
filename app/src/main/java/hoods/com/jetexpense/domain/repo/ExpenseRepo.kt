package hoods.com.jetexpense.domain.repo

import hoods.com.jetexpense.core.utils.ResultState
import hoods.com.jetexpense.domain.models.Expense
import hoods.com.jetexpense.domain.models.Income
import kotlinx.coroutines.flow.Flow

interface ExpenseRepo {
    val income: ResultState<Flow<List<Income>>>
    val expense: ResultState<Flow<List<Expense>>>

    suspend fun insertIncome(income: Income):ResultState<Unit>
    suspend fun insertExpense(expense: Expense):ResultState<Unit>

    fun getIncomeById(id: Int): Flow<Income>
    fun getExpenseById(id: Int): Flow<Expense>

    suspend fun updateIncome(income: Income)
    suspend fun updateExpense(expense: Expense)

    suspend fun deleteIncome(id: Int): Int
    suspend fun deleteExpense(id: Int): Int

}