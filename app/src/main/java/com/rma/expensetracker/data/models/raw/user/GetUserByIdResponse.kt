package com.rma.expensetracker.data.models.raw.user

data class GetUserByIdResponse(
    val result: String,
    val message: String,
    val user: UserGetAllDto
)