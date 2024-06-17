package com.rma.expensetracker.data.models.raw.record

import com.rma.expensetracker.data.models.raw.account.UserID

data class GetRecordsByUserIdRequest(
    val operation: String,
    val user: UserID
)