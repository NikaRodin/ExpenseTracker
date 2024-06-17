package com.rma.expensetracker.data.models.raw.record

data class UpdateRecordRequest(
    val operation: String,
    val transaction: TransactionToUpdate
)

data class TransactionToUpdate(
    //val accountID: String,
    val transactionID: String,
    val amount: Double,
    val base64Photo: String,
    val categoryId: String,
    val currencyId: String,
    val dateOfInsertion: String,
    val description: String,
    val label: String,
    val title: String,
    val transactionType: Int,
    val userId: String,
)