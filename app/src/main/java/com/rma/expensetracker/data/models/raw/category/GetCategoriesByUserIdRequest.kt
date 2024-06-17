package com.rma.expensetracker.data.models.raw.category

import com.rma.expensetracker.data.models.raw.account.UserID

data class GetCategoriesByUserIdRequest(
    val operation: String,
    val user: UserID
)