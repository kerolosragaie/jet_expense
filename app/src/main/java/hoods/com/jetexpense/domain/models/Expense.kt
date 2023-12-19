package hoods.com.jetexpense.domain.models

import java.util.Date

data class Expense(
    val id: Int = 0,
    val title: String,
    val description: String,
    val expenseAmount: Double,
    val category: String,
    val entryDate: String,
    val date: Date,
)