package com.rma.expensetracker.presentation.postlogin.tab3_groups;

import com.rma.expensetracker.data.models.mock.Account

data class GroupAccountState(
    val account: Account,
    val userCount: Int,
)