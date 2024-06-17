package com.rma.expensetracker.data.models.raw.record

import com.rma.expensetracker.data.models.raw.account.AccountID
import com.rma.expensetracker.data.models.raw.account.UserID

data class GetRecordsByAccAndUserIdRequest(
    val operation: String,
    val account: AccountID,
    val user: UserID
)