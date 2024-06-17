package com.rma.expensetracker.data.models.raw.authentication

data class LoginRequest(
    val operation: String,
    val user: LoginCredentials
)

data class LoginCredentials(
    val email: String,
    val password: String
)