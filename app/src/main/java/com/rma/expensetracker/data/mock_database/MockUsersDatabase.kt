package com.rma.expensetracker.data.mock_database

import com.rma.expensetracker.data.models.useful.User

object MockUsersDatabase {
    private var usersList: MutableList<User> = mutableListOf(
        User(
            id = "anneId",
            firstName = "Anne",
            lastName = "Anderson",
            email = "anne.andreson@mail.com",
            userName = "aanderson"
        ),
        User(
            id = "peterId",
            firstName = "Peter",
            lastName = "Peterson",
            email = "peter.peterson@mail.com",
            userName = "ppeterson"
        ),
        User(
            id = "robertId",
            firstName = "Robert",
            lastName = "Robertson",
            email = "robert.robertson@mail.com",
            userName = "rrobertson"
        )
    )

    fun getUserById(userId: String): User {
        return usersList.first {it.id == userId}
    }

    fun getAllUsers(): List<User> {
        return usersList
    }
}