package com.rma.expensetracker.data.models.raw.user

data class GetUsersByAccountIdResponse(
    val result: String,
    val message: String,
    val users: List<UserGetAllDto>
)