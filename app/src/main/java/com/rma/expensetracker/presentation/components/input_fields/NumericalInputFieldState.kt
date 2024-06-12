package com.rma.expensetracker.presentation.components.input_fields

data class NumericalInputFieldState(
    val value: String,
    val onValueChange: (String) -> Unit,
)