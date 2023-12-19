package hoods.com.jetexpense.domain.repo

import hoods.com.jetexpense.core.utils.ResultState
import hoods.com.jetexpense.data.models.entity.ExpenseEntity
import hoods.com.jetexpense.data.models.entity.IncomeEntity
import hoods.com.jetexpense.domain.models.Expense
import hoods.com.jetexpense.domain.models.Income
import kotlinx.coroutines.flow.Flow

interface ExpenseRepo {
    val income: ResultState<Flow<List<Income>>>
    val expense: ResultState<Flow<List<Expense>>>

    suspend fun insertIncome(income: Income):ResultState<Unit>
    suspend fun insertExpense(expense: Expense):ResultState<Unit>

    fun getIncomeById(id: Int): ResultState<Flow<Income>>
    fun getExpenseById(id: Int): ResultState<Flow<Expense>>

    suspend fun updateIncome(income: Income):ResultState<Unit>
    suspend fun updateExpense(expense: Expense):ResultState<Unit>

    suspend fun deleteIncome(id: Int): ResultState<Int>
    suspend fun deleteExpense(id: Int): ResultState<Int>

}