package com.rma.expensetracker.data.models.mock

data class Account(
    val id: String,
    val title: String,
    var balance: Double,
    val isGroupAccount: Boolean
)