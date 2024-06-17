package com.rma.expensetracker.data.interactors

import com.rma.expensetracker.common.Constants
import com.rma.expensetracker.data.models.mapUserGetAllDtoListToUserList
import com.rma.expensetracker.data.models.mapUserGetAllDtoToUser
import com.rma.expensetracker.data.models.raw.account.AccountID
import com.rma.expensetracker.data.models.raw.account.UserID
import com.rma.expensetracker.data.models.raw.user.GetAllUsersRequest
import com.rma.expensetracker.data.models.raw.user.GetUserByIdRequest
import com.rma.expensetracker.data.models.raw.user.GetUsersByAccountIdRequest
import com.rma.expensetracker.data.models.useful.User

object UserInteractor {
    suspend fun getUserById(userId: String): User? {
        val request = GetUserByIdRequest(
            operation = "get_user_for_user_id",
            user = UserID(userId)
        )

        try {
            val response = Constants.apiService.getUserById(request)
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    mapUserGetAllDtoToUser(responseBody.user)
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                    null
                }
            } else {
                println("Error: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
            return null
        }
    }

    suspend fun getUsersByAccountId(accId: String): List<User> {
        val finalUsers = mutableListOf<User>()

        val request = GetUsersByAccountIdRequest(
            operation = "get_users_on_account",
            account = AccountID(accId)
        )

        try {
            val response = Constants.apiService.getUsersByAccountId(request)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    finalUsers.addAll(
                        mapUserGetAllDtoListToUserList(responseBody.users)
                    )
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                }
            } else {
                println("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
        }

        return finalUsers
    }

    suspend fun getUserCountByAccountId(accId: String): Int {
        return getUsersByAccountId(accId).size
    }

    suspend fun getAllUsers(): List<User> {
        val finalUsers = mutableListOf<User>()

        val request = GetAllUsersRequest(
            operation = "get_all_users"
        )

        try {
            val response = Constants.apiService.getAllUsers(request)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    finalUsers.addAll(
                        mapUserGetAllDtoListToUserList(responseBody.users)
                    )
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                }
            } else {
                println("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
        }

        return finalUsers
    }
}