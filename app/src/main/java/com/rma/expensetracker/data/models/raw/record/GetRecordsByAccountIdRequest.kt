package com.rma.expensetracker.data.models.raw.record

import com.rma.expensetracker.data.models.raw.account.AccountID

data class GetRecordsByAccountIdRequest(
    val operation: String,
    val account: AccountID
)