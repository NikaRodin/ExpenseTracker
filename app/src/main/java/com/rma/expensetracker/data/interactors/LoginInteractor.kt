package com.rma.expensetracker.data.interactors

import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.data.mapUserDtoToUser
import com.rma.expensetracker.data.models.raw.LoginCredentials
import com.rma.expensetracker.data.models.raw.LoginRequest

suspend fun dispatchLoginRequest(email: String, password: String) {
    val apiService: ExpenseTrackerApi = RetrofitClient.instance.create(ExpenseTrackerApi::class.java)

    val user = LoginCredentials(email = email, password = password)
    val request = LoginRequest(operation = "login", user = user)

    try {
        val response = apiService.login(request)
        if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null && responseBody.result == "success") {
                CurrentUser.updateCurrentUser(mapUserDtoToUser(responseBody.user[0]))
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