package com.rma.expensetracker.data.repositories

import com.rma.expensetracker.data.remote.ExpenseTrackerApi
import com.rma.expensetracker.data.remote.RetrofitClient
import com.rma.expensetracker.data.remote.dto.LoginCredentials
import com.rma.expensetracker.data.remote.dto.LoginRequest
import com.rma.expensetracker.data.remote.dto.UserDto
import com.rma.expensetracker.data.remote.models.User
import com.rma.expensetracker.presentation.MainActivity.Companion.currentUserRepository

suspend fun dispatchLoginRequest(email: String, password: String) {
    val apiService: ExpenseTrackerApi = RetrofitClient.instance.create(ExpenseTrackerApi::class.java)

    val user = LoginCredentials(email = email, password = password)
    val request = LoginRequest(operation = "login", user = user)

    try {
        val response = apiService.login(request)
        if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null && responseBody.result == "success") {
                currentUserRepository.updateCurrentUser(mapUserDtoToUser(responseBody.user[0]))
            } else {
                println("Error: ${responseBody?.message ?: "Unknown error"}")
            }
        } else {
            println("Error: ${response.code()}")
        }
    } catch (e: Exception) {
        println("Failure: ${e.message}")
    }
}

fun mapUserDtoToUser(userDto: UserDto) : User {
    return User(userDto.firstName, userDto.lastName, userDto.email, userDto.userName)
}
