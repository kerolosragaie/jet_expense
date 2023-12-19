package hoods.com.jetexpense.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import hoods.com.jetexpense.data.models.entity.IncomeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncome(incomeEntity: IncomeEntity)

    @Query("DELETE FROM income_table")
    suspend fun deleteAllIncome()

    @Query("DELETE FROM income_table WHERE income_id = :id")
    suspend fun deleteIncome(id: Int): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateIncome(incomeEntity: IncomeEntity)

    /** Why here not using suspend fun?
     *because we use Flow which going to asynchronously handle all of those coroutine points
     *or suspense points
     */
    @Query("SELECT * FROM income_table")
    fun getAllIncome(): Flow<List<IncomeEntity>>

    @Query("SELECT * FROM income_table Where income_id = :id")
    fun getIncomeById(id: Int): Flow<IncomeEntity>
}