package com.rma.expensetracker.presentation.components

data class InputFieldState(
    val text: String,
    val onTextChange: (String) -> Unit,
)
