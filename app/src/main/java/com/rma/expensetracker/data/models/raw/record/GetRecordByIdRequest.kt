package com.rma.expensetracker.data.models.raw.record

data class GetRecordByIdRequest(
    val operation: String,
    val transaction: TransactionID
)

data class TransactionID(
    val transactionID: String
)