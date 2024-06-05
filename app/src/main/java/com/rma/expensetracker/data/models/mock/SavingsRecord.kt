package com.rma.expensetracker.data.models.mock

import java.time.LocalDate

data class SavingsRecord(
    val id: String,
    val amount: Float,
    val date: LocalDate
)