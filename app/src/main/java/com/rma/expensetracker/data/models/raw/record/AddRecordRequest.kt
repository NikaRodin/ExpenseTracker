package com.rma.expensetracker.data.models.raw.record

data class AddRecordRequest(
    val operation: String,
    val transaction: TransactionToAdd
)

data class TransactionToAdd(
    val accountID: String,
    val amount: Double,
    val base64Photo: String,
    val categoryId: String,
    val currencyId: String,
    val dateOfInsertion: String, //
    val description: String,
    val label: String,
    val title: String,
    val transactionType: Int,
    val userId: String,
)