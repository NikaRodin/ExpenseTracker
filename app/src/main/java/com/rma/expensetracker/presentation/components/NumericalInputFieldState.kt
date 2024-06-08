package com.rma.expensetracker.presentation.components

data class NumericalInputFieldState(
    val value: String,
    val onValueChange: (String) -> Unit,
)