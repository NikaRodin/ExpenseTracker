package com.rma.expensetracker.presentation.components.input_fields

data class InputFieldState(
    val text: String,
    val onTextChange: (String) -> Unit,
)
