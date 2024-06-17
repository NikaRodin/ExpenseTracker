package com.rma.expensetracker.data.models.raw.user

import com.rma.expensetracker.data.models.raw.account.UserID

data class GetUserByIdRequest(
    val operation: String,
    val user: UserID
)