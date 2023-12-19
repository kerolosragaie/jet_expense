package hoods.com.jetexpense.data.mappers

import hoods.com.jetexpense.data.models.entity.ExpenseEntity
import hoods.com.jetexpense.domain.models.Expense


fun ExpenseEntity.fromEntityToModel(): Expense = Expense(
    id,
    title,
    description,
    expenseAmount,
    category,
    entryDate,
    date,
)

fun Expense.fromModelToEntity(): ExpenseEntity = ExpenseEntity(
    id,
    title,
    description,
    expenseAmount,
    category,
    entryDate,
    date,
)