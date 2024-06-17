package com.rma.expensetracker.data.mock_database

import com.rma.expensetracker.data.models.mock.Account
import com.rma.expensetracker.data.models.mock.User

object MockGroupDatabase {
    private var userAccConnections: MutableList<UserAccConnection> = mutableListOf(
        UserAccConnection(
            userId = "anneId",
            accountId = "anneOsobniId"
        ),
        UserAccConnection(
            userId = "peterId",
            accountId = "peterOsobniId"
        ),
        UserAccConnection(
            userId = "robertId",
            accountId = "robertOsobniId"
        ),

        // Grupni računi
        UserAccConnection(
            userId = "anneId",
            accountId = "annePeterObiteljskiId"
        ),
        UserAccConnection(
            userId = "peterId",
            accountId = "annePeterObiteljskiId"
        ),
        UserAccConnection(
            userId = "peterId",
            accountId = "annePeterRobertPoslovniId"
        ),
        UserAccConnection(
            userId = "robertId",
            accountId = "annePeterRobertPoslovniId"
        ),
        UserAccConnection(
            userId = "anneId",
            accountId = "annePeterRobertPoslovniId"
        )
    )

    fun addUserAccountConnections(userIds: List<String>, accId: String) {
        userIds.forEach { userId -> userAccConnections.add(UserAccConnection(userId, accId)) }
    }

    fun removeUserAccountConnections(userIds: List<String>, accId: String) {
        userIds.forEach { userId ->
            userAccConnections.removeIf { it.userId == userId && it.accountId == accId }
        }
    }

    fun getAccountsByUserId(userId: String): List<Account> {
        val filteredConnections = userAccConnections.filter { it.userId == userId }
        val accountsList = mutableListOf<Account>()

        filteredConnections.forEach { connection ->
            accountsList.add(MockAccountsDatabase.getAccountById(connection.accountId))
        }

        return accountsList
    }

    fun getUsersByAccountId(accId: String): List<User> {
        val filteredConnections = userAccConnections.filter { it.accountId == accId }
        val usersList = mutableListOf<User>()

        filteredConnections.forEach { connection ->
            usersList.add(MockUsersDatabase.getUserById(connection.userId))
        }

        return usersList
    }
}

data class UserAccConnection(
    val userId: String,
    val accountId: String
)