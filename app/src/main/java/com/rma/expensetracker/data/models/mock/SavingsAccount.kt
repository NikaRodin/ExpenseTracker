package com.rma.expensetracker.data.models.mock

import androidx.compose.ui.graphics.vector.ImageVector

data class SavingsAccount(
    val id: String,
    val title: String,
        val balance: Double,
    val icon: ImageVector? = null  //TODO
)