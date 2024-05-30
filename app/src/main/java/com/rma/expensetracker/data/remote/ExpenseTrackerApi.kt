package com.rma.expensetracker.data.remote

import com.rma.expensetracker.data.remote.dto.LoginRequest
import com.rma.expensetracker.data.remote.dto.LoginResponse
import com.rma.expensetracker.data.remote.dto.RegistrationRequest
import com.rma.expensetracker.data.remote.dto.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ExpenseTrackerApi {
    @POST("index.php")
    suspend fun registerUser(@Body registrationRequest: RegistrationRequest): Response<RegistrationResponse>
    @POST("index.php")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}