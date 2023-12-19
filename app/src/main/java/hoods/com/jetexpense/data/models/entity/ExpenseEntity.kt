package hoods.com.jetexpense.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "expense_table")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "expense_id")
    val id: Int = 0,
    val title: String,
    val description: String,
    @ColumnInfo(name = "expense_amount")
    val expenseAmount: Double,
    val category: String,
    @ColumnInfo(name = "entry_date")
    val entryDate: String,
    val date: Date,
)
