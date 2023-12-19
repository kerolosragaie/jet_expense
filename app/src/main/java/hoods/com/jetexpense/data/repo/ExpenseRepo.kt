package hoods.com.jetexpense.data.repo

import hoods.com.jetexpense.data.models.entity.ExpenseEntity
import hoods.com.jetexpense.data.models.entity.IncomeEntity
import kotlinx.coroutines.flow.Flow

interface ExpenseRepo {
    val income: Flow<List<IncomeEntity>>
    val expense: Flow<List<ExpenseEntity>>

    suspend fun insertIncome(incomeEntity: IncomeEntity)
    suspend fun insertExpense(expenseEntity: ExpenseEntity)

    fun getIncomeById(id: Int): Flow<IncomeEntity>
    fun getExpenseById(id: Int): Flow<ExpenseEntity>

    suspend fun updateIncome(incomeEntity: IncomeEntity)
    suspend fun updateExpense(expenseEntity: ExpenseEntity)

    suspend fun deleteIncome(id: Int): Int
    suspend fun deleteExpense(id: Int): Int

}