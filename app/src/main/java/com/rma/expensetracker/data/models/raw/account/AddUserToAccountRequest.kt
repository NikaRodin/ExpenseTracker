package com.rma.expensetracker.data.models.raw.account

data class AddUserToAccountRequest(
    val operation: String,
    val account: AccountWithSavingsFlagID,
    val user: UserID
)

data class AccountWithSavingsFlagID(
    val accountID: String,
    val isSavings: Int
)