package com.rma.expensetracker.data.models.raw.account

data class GetAccountByIdRequest(
    val operation: String,
    val account: AccountID
)

data class AccountID(
    val accountID: String
)