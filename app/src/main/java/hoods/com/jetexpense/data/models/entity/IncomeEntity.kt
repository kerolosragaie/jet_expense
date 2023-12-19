package hoods.com.jetexpense.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "income_table")
data class IncomeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "income_id")
    val id: Int = 0,
    val title: String,
    val description: String,
    @ColumnInfo(name = "income_amount")
    val incomeAmount: Double,
    @ColumnInfo(name = "entry_date")
    val entryDate: String,
    val date: Date,
)
