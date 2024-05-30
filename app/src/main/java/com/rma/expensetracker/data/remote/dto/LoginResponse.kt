package com.rma.expensetracker.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    val result: String,
    val message: String,
    val user: List<UserDto>    //val user: List<User>
)

data class UserDto(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String,
    @SerializedName("user_name")
    val userName: String?  //zašto je null - pitati
)

//TODO mapping
