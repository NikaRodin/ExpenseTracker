package com.rma.expensetracker.presentation.prelogin.components

data class InputFieldState(
    //val isEnabled: Boolean = true,
    //val isVisible: Boolean = true,
    //val isError: Boolean = false,
    val text: String,
    val onTextChange: (String) -> Unit,
)
