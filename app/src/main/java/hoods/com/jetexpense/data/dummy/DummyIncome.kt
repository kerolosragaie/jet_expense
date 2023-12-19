package hoods.com.jetexpense.data.dummy

import hoods.com.jetexpense.core.utils.DateFormatter.formatDate
import hoods.com.jetexpense.domain.models.Income
import java.util.Calendar

val date = Calendar.getInstance()

val dummyIncomeList = listOf(
    Income(
        id = 0,
        title = "Freelancing",
        description = "Payment from upwork project",
        entryDate = formatDate(date),
        date = date.time,
        incomeAmount = 1000.0,
    ),
    Income(
        1,
        incomeAmount = 6000.0,
        title = "Salary",
        description = "Payment from permanent job",
        entryDate = formatDate(date),
        date = date.time
    ),
    Income(
        2,
        incomeAmount = 3000.0,
        title = "Side project",
        description = "Payment from upwork project",
        entryDate = formatDate(date),
        date = date.time
    ),
    Income(
        3,
        incomeAmount = 1000.0,
        title = "Tutor Project",
        description = "Payment from students for coding session",
        entryDate = formatDate(date),
        date = date.time
    ),
    Income(
        4,
        incomeAmount = 1000.0,
        title = "Vending Machine",
        description = "Payment from selling soft drink",
        entryDate = formatDate(date),
        date = date.time
    ),
)