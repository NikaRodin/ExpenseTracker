package com.rma.expensetracker.data.models.raw.account

data class GetUserPersonalAccountsRequest(
    val operation: String,
    val user: UserID
)

data class UserID(
    val userID: String
)