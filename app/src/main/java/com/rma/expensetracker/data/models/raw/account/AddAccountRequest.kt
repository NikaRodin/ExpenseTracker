package com.rma.expensetracker.data.models.raw.account

data class AddAccountRequest(
    val operation: String,
    val account: AccountToAdd,
    val user: UserID
)

data class AccountToAdd(
    val balance: Double,
    val currencyID: String,
    val isGroupAccount: Int,
    val isLimited: Int,
    val limit: Double,
    val name: String,
    val photo: String
)