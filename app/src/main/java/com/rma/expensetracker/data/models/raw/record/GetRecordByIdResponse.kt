package com.rma.expensetracker.data.models.raw.record

data class GetRecordByIdResponse(
    val result: String,
    val message: String,
    val transaction: Transaction
)

data class Transaction(
    val accountID: String,
    val amount: String,
    val balanceAfterInsertion: String,
    val base64Photo: String,
    val categoryID: String,
    val currencyID: String,
    val dateOfInsertion: String,
    val description: String,
    val isParent: String,
    val lable: String,
    val title: String,
    val type: String,
    val uniqueID: String,
    val userID: String
)