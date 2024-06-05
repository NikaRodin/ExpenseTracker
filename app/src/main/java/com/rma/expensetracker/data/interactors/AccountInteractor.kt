package com.rma.expensetracker.data.interactors

import com.rma.expensetracker.data.mock_database.MockAccountsDatabase
import com.rma.expensetracker.data.mock_database.MockGroupDatabase
import com.rma.expensetracker.data.models.mock.Account

object AccountInteractor {
    fun getAccountById(accId: String): Account {
        return MockAccountsDatabase.getAccountById(accId)
    }

    fun getAccountsByUserId(userId: String): List<Account> {
        return MockGroupDatabase.getAccountsByUserId(userId)
    }


    fun addAccount(userIds: List<String>, account: Account) {
        MockAccountsDatabase.addAccount(userIds, account)
    }
}