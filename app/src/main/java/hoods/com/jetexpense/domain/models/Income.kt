package hoods.com.jetexpense.domain.models

import java.util.Date

data class Income(
    val id: Int = 0,
    val title: String,
    val description: String,
    val incomeAmount: Double,
    val entryDate: String,
    val date: Date,
)
