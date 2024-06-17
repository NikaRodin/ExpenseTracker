package com.rma.expensetracker.data.models.raw.authentication

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    val operation: String,
    val user: RegistrationCredentials
)

data class RegistrationCredentials(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("user_name")
    val userName: String,
    val email: String,
    val password: String,
)