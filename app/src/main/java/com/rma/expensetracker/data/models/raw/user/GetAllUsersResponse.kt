package com.rma.expensetracker.data.models.raw.user

data class GetAllUsersResponse(
    val message: String,
    val result: String,
    val users: List<UserGetAllDto>
)
data class UserGetAllDto(
    val email: String,
    val name: String,
    val surname: String,
    val userID: String,
    val username: String
)