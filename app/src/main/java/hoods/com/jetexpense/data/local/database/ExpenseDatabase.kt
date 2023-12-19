package hoods.com.jetexpense.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hoods.com.jetexpense.data.local.dao.ExpenseDao
import hoods.com.jetexpense.data.local.dao.IncomeDao
import hoods.com.jetexpense.data.local.typeconverter.DateTypeConverter
import hoods.com.jetexpense.data.models.entity.ExpenseEntity
import hoods.com.jetexpense.data.models.entity.IncomeEntity

@Database(
    entities = [ExpenseEntity::class, IncomeEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(value = [DateTypeConverter::class])
abstract class ExpenseDatabase : RoomDatabase() {
    abstract val expenseDao: ExpenseDao
    abstract val incomeDao: IncomeDao
}