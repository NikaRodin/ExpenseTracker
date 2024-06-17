package com.rma.expensetracker.data.interactors

import com.rma.expensetracker.common.Constants.apiService
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.data.models.mapAccountDtoListToAccountList
import com.rma.expensetracker.data.models.mapAccountWithGroupFlagDtoToAccount
import com.rma.expensetracker.data.models.raw.account.AccountID
import com.rma.expensetracker.data.models.raw.account.AccountToAdd
import com.rma.expensetracker.data.models.raw.account.AccountToUpdate
import com.rma.expensetracker.data.models.raw.account.AccountWithSavingsFlagID
import com.rma.expensetracker.data.models.raw.account.AddAccountRequest
import com.rma.expensetracker.data.models.raw.account.AddUserToAccountRequest
import com.rma.expensetracker.data.models.raw.account.GetAccountByIdRequest
import com.rma.expensetracker.data.models.raw.account.GetUserGroupAccountsRequest
import com.rma.expensetracker.data.models.raw.account.GetUserPersonalAccountsRequest
import com.rma.expensetracker.data.models.raw.account.RemoveUserFromAccountRequest
import com.rma.expensetracker.data.models.raw.account.UpdateAccountRequest
import com.rma.expensetracker.data.models.raw.account.UserID
import com.rma.expensetracker.data.models.useful.Account

object AccountInteractor {
    suspend fun getAccountById(accId: String): Account? {
        val request = GetAccountByIdRequest(
            operation = "get_account",
            account = AccountID(accId)
        )

        try {
            val response = apiService.getAccountById(request)
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    mapAccountWithGroupFlagDtoToAccount(responseBody.account)
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

    suspend fun getAccountsByUserId(userId: String): List<Account> {
        val finalAccounts = mutableListOf<Account>()

        val requestPersonal = GetUserPersonalAccountsRequest(
            operation = "get_user_personal_accounts",
            user = UserID(userId)
        )
        val requestGroup = GetUserGroupAccountsRequest(
            operation = "get_user_group_accounts",
            user = UserID(userId)
        )

        try {
            val response = apiService.getUserPersonalAccounts(requestPersonal)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    finalAccounts.addAll(
                        mapAccountDtoListToAccountList(
                            responseBody.accounts,
                            false
                        )
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

        try {
            val response = apiService.getUserGroupAccounts(requestGroup)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    finalAccounts.addAll(
                        mapAccountDtoListToAccountList(
                            responseBody.accounts,
                            true
                        )
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

        return finalAccounts
    }


    suspend fun addAccount(userIds: List<String>, account: Account): Boolean {
        /*
        MockAccountsDatabase.accountsList.add(account)
        MockGroupDatabase.addUserAccountConnections(userIds, account.id)*/

        val currentUser = CurrentUser.currentUser.value
        if(CurrentUser.currentUser.value == null) {
            println("Current user je null!")
            return false
        }
        val request = AddAccountRequest(
            operation = "create_account",
            account = AccountToAdd(
                balance = account.balance,
                currencyID = "6653764ab60e4",
                isGroupAccount = if(userIds.isNotEmpty()) 1 else 0,
                isLimited = 0,
                limit = 0.0,
                name = account.title,
                photo = "slika",
            ),
            user = UserID(currentUser!!.id)
        )

        try {
            val response = apiService.addAccount(request)
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    return addUsersToAccount(userIds, accId = responseBody.accountID)
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                    false
                }
            } else {
                println("Error: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
            return false
        }
    }

    suspend fun addUsersToAccount(userIds: List<String>, accId: String): Boolean {
        userIds.forEach { userId ->
            val request = AddUserToAccountRequest(
                operation = "add_user_to_account",
                account = AccountWithSavingsFlagID(accId, 0),
                user = UserID(userId)
            )

            try {
                val response = apiService.addUserToAccount(request)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody == null || responseBody.result != "success") {
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

        }
        return true
    }

    suspend fun removeUsersFromAccount(userIds: List<String>, accId: String): Boolean {
        userIds.forEach { userId ->
            val request = RemoveUserFromAccountRequest(
                operation = "remove_user_from_account",
                account = AccountWithSavingsFlagID(accId, 0),
                user = UserID(userId)
            )

            try {
                val response = apiService.removeUserFromAccount(request)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody == null || responseBody.result != "success") {
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

        }
        return true
    }

    suspend fun updateAccount(accId: String, newAccount: Account): Boolean {
        val request = UpdateAccountRequest(
            operation = "update_account",
            account = AccountToUpdate(
                accountID = accId,
                currencyID = "6653764ab60e4",
                isLimited = 0,
                limit = 0.0,
                name = newAccount.title,
                photo = "slika",
            )
        )

        try {
            val response = apiService.updateAccount(request)
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    true
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                    false
                }
            } else {
                println("Error: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
            return false
        }
    }
}