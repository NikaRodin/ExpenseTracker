package com.rma.expensetracker.data.mock_database

import com.rma.expensetracker.data.models.mock.Account

object MockAccountsDatabase {
    private var accountsList: MutableList<Account> = mutableListOf(
        Account(
            id = "anneOsobniId",
            title  = "Osobni račun",
            balance = 680.00,
            isGroupAccount = false
        ),
        Account(
            id = "peterOsobniId",
            title  = "Osobni račun",
            balance = 500.00,
            isGroupAccount = false
        ),
        Account(
            id = "robertOsobniId",
            title  = "Osobni račun",
            balance = -300.00,
            isGroupAccount = false
        ),
        Account(
            id = "annePeterObiteljskiId",
            title  = "Obiteljski račun",
            balance = 1400.00,
            isGroupAccount = true
        ),
        Account(
            id = "annePeterRobertPoslovniId",
            title  = "Poslovni račun",
            balance = 11850.00,
            isGroupAccount = true
        ),
    )

    fun getAccountById(accId: String): Account {
        return accountsList.first { it.id == accId }
    }

    fun addAccount(userIds: List<String>, account: Account) {
        accountsList.add(account)
        MockGroupDatabase.addUserAccountConnections(userIds, account.id)
    }

    fun updateAccount(accId: String, newAccount: Account) {
        val account = getAccountById(accId)
        val updatedAccount = account.copy(
            title = newAccount.title,
            balance = newAccount.balance,
            isGroupAccount = newAccount.isGroupAccount
        )
        accountsList[accountsList.indexOf(account)] = updatedAccount
    }

    fun setBalance(accId: String, balance: Double) {
        accountsList.find { it.id == accId }?.balance = balance
    }
}