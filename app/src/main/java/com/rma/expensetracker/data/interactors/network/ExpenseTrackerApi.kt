package com.rma.expensetracker.data.interactors.network

import com.rma.expensetracker.data.models.raw.LoginRequest
import com.rma.expensetracker.data.models.raw.LoginResponse
import com.rma.expensetracker.data.models.raw.RegistrationRequest
import com.rma.expensetracker.data.models.raw.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ExpenseTrackerApi {
    @POST("index.php")
    suspend fun registerUser(@Body registrationRequest: RegistrationRequest): Response<RegistrationResponse>
    @POST("index.php")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}