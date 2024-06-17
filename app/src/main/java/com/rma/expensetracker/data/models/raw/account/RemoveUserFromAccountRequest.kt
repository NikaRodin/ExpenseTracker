package com.rma.expensetracker.data.models.raw.account

data class RemoveUserFromAccountRequest(
    val account: AccountWithSavingsFlagID,
    val operation: String,
    val user: UserID
)