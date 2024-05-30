package com.rma.expensetracker.data.repositories

import com.rma.expensetracker.data.remote.ExpenseTrackerApi
import com.rma.expensetracker.data.remote.RetrofitClient
import com.rma.expensetracker.data.remote.dto.RegistrationCredentials
import com.rma.expensetracker.data.remote.dto.RegistrationRequest

suspend fun dispatchRegistrationRequest(
    email: String,
    password: String,
    firstName: String,
    lastName: String,
    userName: String
) {
    val apiService: ExpenseTrackerApi = RetrofitClient.instance.create(ExpenseTrackerApi::class.java)

    val user = RegistrationCredentials(
        email = email,
        password = password,
        firstName = firstName,
        lastName = lastName,
        userName = userName
    )
    val request = RegistrationRequest(operation = "register_user", user = user)

    try {
        val response = apiService.registerUser(request)
        if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null && responseBody.result == "success") {
                dispatchLoginRequest(email = email, password = password)
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