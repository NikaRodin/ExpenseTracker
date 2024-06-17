package com.rma.expensetracker.data.interactors

import com.rma.expensetracker.common.Constants.apiService
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.data.models.mapUserDtoToUser
import com.rma.expensetracker.data.models.raw.authentication.LoginCredentials
import com.rma.expensetracker.data.models.raw.authentication.LoginRequest
import com.rma.expensetracker.data.models.raw.authentication.RegistrationCredentials
import com.rma.expensetracker.data.models.raw.authentication.RegistrationRequest

object AuthenticationInteractor {
    suspend fun dispatchLoginRequest(email: String, password: String): Boolean {
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
                    return false
                }
            } else {
                println("Error: ${response.code()}")
                return false
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
            return false
        }
        return true
    }

    suspend fun dispatchRegistrationRequest(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        userName: String
    ): Boolean {
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
                    return false
                }
            } else {
                println("Error: ${response.code()}")
                return false
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
            return false
        }
        return true
    }
}