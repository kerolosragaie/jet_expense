package hoods.com.jetexpense.data.repo

import hoods.com.jetexpense.core.utils.ResultState
import hoods.com.jetexpense.data.local.dao.ExpenseDao
import hoods.com.jetexpense.data.local.dao.IncomeDao
import hoods.com.jetexpense.data.mappers.fromEntityToModel
import hoods.com.jetexpense.data.mappers.fromModelToEntity
import hoods.com.jetexpense.di.IoDispatcher
import hoods.com.jetexpense.domain.models.Expense
import hoods.com.jetexpense.domain.models.Income
import hoods.com.jetexpense.domain.repo.ExpenseRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExpenseRepoImpl @Inject constructor(
    private val incomeDao: IncomeDao,
    private val expenseDao: ExpenseDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ExpenseRepo {
    override val income: ResultState<Flow<List<Income>>>
        get() = try {
            val result = incomeDao.getAllIncome()
                .map { incomeList ->
                    incomeList.map { it.fromEntityToModel() }
                }
            ResultState.Success(data = result)
        } catch (e: Exception) {
            ResultState.Failure(e.message)
        }
    override val expense: ResultState<Flow<List<Expense>>>
        get() = try {
            val result = expenseDao.getAllExpense()
                .map { expenseList ->
                    expenseList.map { it.fromEntityToModel() }
                }
            ResultState.Success(data = result)
        } catch (e: Exception) {
            ResultState.Failure(e.message)
        }

    override suspend fun insertIncome(income: Income) = withContext(dispatcher) {
        try {
            incomeDao.insertIncome(income.fromModelToEntity())
            ResultState.Success<Unit>()
        } catch (e: Exception) {
            ResultState.Failure(e.message)
        }
    }

    override suspend fun insertExpense(expense: Expense) = withContext(dispatcher) {
        try {
            expenseDao.insertExpense(expense.fromModelToEntity())
            ResultState.Success<Unit>()
        } catch (e: Exception) {
            ResultState.Failure(e.message)
        }
    }

    override fun getIncomeById(id: Int): ResultState<Flow<Income>> = try {
        val income = incomeDao.getIncomeById(id).map { it.fromEntityToModel() }
        ResultState.Success(income)
    } catch (e: Exception) {
        ResultState.Failure(e.message)
    }

    override fun getExpenseById(id: Int): ResultState<Flow<Expense>> = try {
        val expense = expenseDao.getExpenseById(id).map { it.fromEntityToModel() }
        ResultState.Success(expense)
    } catch (e: Exception) {
        ResultState.Failure(e.message)
    }

    override suspend fun updateIncome(income: Income): ResultState<Unit> = withContext(dispatcher) {
        try {
            incomeDao.updateIncome(income.fromModelToEntity())
            ResultState.Success()
        } catch (e: Exception) {
            ResultState.Failure(e.message)
        }
    }

    override suspend fun updateExpense(expense: Expense): ResultState<Unit> =
        withContext(dispatcher) {
            try {
                expenseDao.updateExpense(expense.fromModelToEntity())
                ResultState.Success()
            } catch (e: Exception) {
                ResultState.Failure(e.message)
            }
        }

    override suspend fun deleteIncome(id: Int): ResultState<Int> = withContext(dispatcher) {
        try {
            ResultState.Success(incomeDao.deleteIncome(id))
        } catch (e: Exception) {
            ResultState.Failure(e.message)
        }
    }

    override suspend fun deleteExpense(id: Int): ResultState<Int> = withContext(dispatcher) {
        try {
            ResultState.Success(expenseDao.deleteExpense(id))
        } catch (e: Exception) {
            ResultState.Failure(e.message)
        }
    }
}