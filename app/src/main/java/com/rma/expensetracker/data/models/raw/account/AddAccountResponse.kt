package com.rma.expensetracker.data.models.raw.account

data class AddAccountResponse(
    val result: String,
    val message: String,
    val accountID: String
)