package com.rma.expensetracker.data.models.raw.record

data class GetRecordsByUserIdResponse(
    val message: String,
    val result: String,
    val transactions: List<Transaction>
)