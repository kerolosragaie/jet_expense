package hoods.com.jetexpense.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import hoods.com.jetexpense.data.models.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expenseEntity: ExpenseEntity)

    @Query("DELETE FROM expense_table")
    suspend fun deleteAllExpense()

    @Query("DELETE FROM expense_table WHERE expense_id = :id")
    suspend fun deleteExpense(id: String): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateExpense(expenseEntity: ExpenseEntity)

    /** Why here not using suspend fun?
     *because we use Flow which going to asynchronously handle all of those coroutine points
     *or suspense points
     */
    @Query("SELECT * FROM expense_table")
    fun getAllExpense(): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expense_table Where expense_id = :id")
    fun getExpenseById(id: Int): Flow<ExpenseEntity>
}