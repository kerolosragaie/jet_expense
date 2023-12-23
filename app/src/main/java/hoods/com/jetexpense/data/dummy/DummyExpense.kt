package hoods.com.jetexpense.data.dummy

import hoods.com.jetexpense.core.utils.DateFormatter.formatDate
import hoods.com.jetexpense.domain.models.Expense

val dummyExpenseList = listOf(
    Expense(
        id = 0,
        entryDate = formatDate(date),
        expenseAmount = 50.0,
        category = "entertainment",
        title = "Netflix Subscription",

        description = "Payed Netflix for monthly subscription",
        date = date.time
    ),
    Expense(
        id = 1,
        entryDate = formatDate(date),
        expenseAmount = 100.0,
        category = "Food and Drinks",
        title = "Groceries",
        description = "Payed  for monthly groceries",
        date = date.time
    ),
    Expense(
        id = 2,
        entryDate = formatDate(date),
        expenseAmount = 500.0,
        category = "Vehicle",
        title = "Car Maintenance",
        description = "Payed  for car tire, brake pad and oil change",
        date = date.time
    ),
    Expense(
        id = 3,
        entryDate = formatDate(date),
        expenseAmount = 1000.0,
        category = "Housing",
        title = "Rent",
        description = "Payed for monthly rent for apartment",
        date = date.time
    ),
    Expense(
        id = 4,
        entryDate = formatDate(date),
        expenseAmount = 100.0,
        category = "Tech",
        title = "Computer",
        description = "Purchased a computer for work",
        date = date.time
    ),

    )