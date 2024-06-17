package com.rma.expensetracker.data.models.raw.user

import com.rma.expensetracker.data.models.raw.account.AccountID

data class GetUsersByAccountIdRequest(
    val account: AccountID,
    val operation: String
)