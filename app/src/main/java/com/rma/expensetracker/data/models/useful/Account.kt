package com.rma.expensetracker.data.models.useful

data class Account(
    val id: String,
    val title: String,
    var balance: Double,
    val isGroupAccount: Boolean
)