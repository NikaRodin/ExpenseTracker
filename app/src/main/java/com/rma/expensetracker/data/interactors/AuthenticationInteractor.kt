package com.rma.expensetracker.data.interactors

import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.data.interactors.network.ExpenseTrackerApi
import com.rma.expensetracker.data.interactors.network.RetrofitClient
import com.rma.expensetracker.data.models.mapUserDtoToUser
import com.rma.expensetracker.data.models.raw.LoginCredentials
import com.rma.expensetracker.data.models.raw.LoginRequest
import com.rma.expensetracker.data.models.raw.RegistrationCredentials
import com.rma.expensetracker.data.models.raw.RegistrationRequest

object AuthenticationInteractor {

    fun userLogin(email: String, password: String): Boolean {
        val allUsers = UserInteractor.getAllUsers()
        val user = allUsers.find { user -> user.email == email }
        CurrentUser.updateCurrentUser(user)
        return user != null
    }

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
}