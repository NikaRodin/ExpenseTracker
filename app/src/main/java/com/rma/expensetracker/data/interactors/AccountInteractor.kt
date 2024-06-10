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

    fun updateBalance(accId: String) {
        val records = RecordInteractor.getRecordsByAccountId(accId)
        var updatedBalance = 0.00
        records.forEach {
            updatedBalance += it.amount
        }
        MockAccountsDatabase.setBalance(accId, updatedBalance)
    }
}