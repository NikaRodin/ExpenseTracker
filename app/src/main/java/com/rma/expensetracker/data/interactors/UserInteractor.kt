package com.rma.expensetracker.data.interactors

import com.rma.expensetracker.data.mock_database.MockGroupDatabase
import com.rma.expensetracker.data.mock_database.MockUsersDatabase
import com.rma.expensetracker.data.models.mock.User

object UserInteractor {
    fun getUserById(userId: String): User {
        return MockUsersDatabase.getUserById(userId)
    }

    fun getUsersByAccountId(accId: String): List<User> {
        return MockGroupDatabase.getUsersByAccountId(accId)
    }

    fun getUserCountByAccountId(accId: String): Int {
        return getUsersByAccountId(accId).size
    }

    fun getAllUsers(): List<User> {
        return MockUsersDatabase.getAllUsers()
    }
}