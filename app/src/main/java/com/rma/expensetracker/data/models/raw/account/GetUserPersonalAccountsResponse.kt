package com.rma.expensetracker.data.models.raw.account

data class GetUserPersonalAccountsResponse(
    val result: String,
    val message: String,
    val accounts: List<AccountDto>
)

data class AccountDto(
    val accountID: String,
    val balance: String,
    val balanceLimit: String,
    val currencyID: String,
    val isLimited: String,
    val name: String,
    val photo: String
)