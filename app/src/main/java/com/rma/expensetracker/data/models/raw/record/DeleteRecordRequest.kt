package com.rma.expensetracker.data.models.raw.record

data class DeleteRecordRequest(
    val operation: String,
    val transaction: TransactionID
)