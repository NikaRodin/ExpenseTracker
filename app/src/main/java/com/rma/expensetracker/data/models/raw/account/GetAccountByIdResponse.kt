package com.rma.expensetracker.data.models.raw.account

data class GetAccountByIdResponse(
    val result: String,
    val message: String,
    val account: AccountWithGroupFlagDto,
)

data class AccountWithGroupFlagDto(
    val accountID: String,
    val balance: String,
    val balanceLimit: String,
    val currencyID: String,
    val isGroupAccount: String,
    val isLimited: String,
    val name: String,
    val photo: String
)