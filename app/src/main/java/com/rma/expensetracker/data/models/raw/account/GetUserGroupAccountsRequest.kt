package com.rma.expensetracker.data.models.raw.account

data class GetUserGroupAccountsRequest(
    val operation: String,
    val user: UserID
)