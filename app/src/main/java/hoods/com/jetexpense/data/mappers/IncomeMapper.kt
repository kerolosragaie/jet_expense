package hoods.com.jetexpense.data.mappers

import hoods.com.jetexpense.data.models.entity.IncomeEntity
import hoods.com.jetexpense.domain.models.Income

fun IncomeEntity.fromEntityToModel(): Income = Income(
    id,
    title,
    description,
    incomeAmount,
    entryDate,
    date,
)

fun Income.fromModelToEntity(): IncomeEntity = IncomeEntity(
    id,
    title,
    description,
    incomeAmount,
    entryDate,
    date,
)