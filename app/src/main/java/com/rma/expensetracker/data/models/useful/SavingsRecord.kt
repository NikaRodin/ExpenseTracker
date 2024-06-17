package com.rma.expensetracker.data.models.useful

import java.time.LocalDate

data class SavingsRecord(
    val id: String,
    val amount: Double,
    val date: LocalDate
)