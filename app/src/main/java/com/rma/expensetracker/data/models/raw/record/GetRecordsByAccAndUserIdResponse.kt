package com.rma.expensetracker.data.models.raw.record

data class GetRecordsByAccAndUserIdResponse(
    val result: String,
    val message: String,
    val transactions: List<Transaction>
)