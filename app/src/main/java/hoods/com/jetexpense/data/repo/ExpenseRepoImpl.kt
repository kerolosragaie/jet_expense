package hoods.com.jetexpense.data.repo

import hoods.com.jetexpense.data.local.dao.ExpenseDao
import hoods.com.jetexpense.data.local.dao.IncomeDao
import hoods.com.jetexpense.data.models.entity.ExpenseEntity
import hoods.com.jetexpense.data.models.entity.IncomeEntity
import hoods.com.jetexpense.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExpenseRepoImpl @Inject constructor(
    private val incomeDao: IncomeDao,
    private val expenseDao: ExpenseDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ExpenseRepo {
    override val income: Flow<List<IncomeEntity>>
        get() = incomeDao.getAllIncome()
    override val expense: Flow<List<ExpenseEntity>>
        get() = expenseDao.getAllExpense()

    override suspend fun insertIncome(incomeEntity: IncomeEntity) = withContext(dispatcher) {
        incomeDao.insertIncome(incomeEntity)
    }

    override suspend fun insertExpense(expenseEntity: ExpenseEntity) = withContext(dispatcher) {
        expenseDao.insertExpense(expenseEntity)
    }

    override fun getIncomeById(id: Int): Flow<IncomeEntity> = incomeDao.getIncomeById(id)

    override fun getExpenseById(id: Int): Flow<ExpenseEntity> = expenseDao.getExpenseById(id)

    override suspend fun updateIncome(incomeEntity: IncomeEntity) = withContext(dispatcher) {
        incomeDao.updateIncome(incomeEntity)
    }

    override suspend fun updateExpense(expenseEntity: ExpenseEntity) = withContext(dispatcher) {
        expenseDao.updateExpense(expenseEntity)
    }

    override suspend fun deleteIncome(id: Int): Int = withContext(dispatcher) {
        incomeDao.deleteIncome(id)
    }

    override suspend fun deleteExpense(id: Int): Int = withContext(dispatcher) {
        expenseDao.deleteExpense(id)
    }
}