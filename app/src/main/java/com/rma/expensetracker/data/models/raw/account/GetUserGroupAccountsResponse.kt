package com.rma.expensetracker.data.models.raw.account

data class GetUserGroupAccountsResponse(
    val result: String,
    val message: String,
    val accounts: List<AccountDto>
)