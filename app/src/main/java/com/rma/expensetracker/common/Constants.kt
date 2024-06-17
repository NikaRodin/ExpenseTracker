package com.rma.expensetracker.common

import com.rma.expensetracker.data.interactors.network.ExpenseTrackerApi
import com.rma.expensetracker.data.interactors.network.RetrofitClient

object Constants {
    val apiService: ExpenseTrackerApi = RetrofitClient.instance.create(ExpenseTrackerApi::class.java)
}