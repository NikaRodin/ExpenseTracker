package com.rma.expensetracker.data.models.raw.account

data class UpdateAccountRequest(
    val account: AccountToUpdate,
    val operation: String
)

data class AccountToUpdate(
    val accountID: String,
    val currencyID: String,
    val isLimited: Int,
    val limit: Double,
    val name: String,
    val photo: String
)