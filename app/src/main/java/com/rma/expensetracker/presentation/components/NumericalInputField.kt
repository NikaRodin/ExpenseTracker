package com.rma.expensetracker.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun NumericalInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    val maxLength = 10
    OutlinedTextField(
        value = value,
        label = label,
        onValueChange = {
            if (it.length <= maxLength) {
                val filteredText = it.filter { char -> char.isDigit() || char == '.' }
                val decimalIndex = filteredText.indexOf('.')
                val validText = if (decimalIndex >= 0) {
                    val integerPart = filteredText.substring(0, decimalIndex)
                    val decimalPart = filteredText.substring(decimalIndex + 1).take(2)
                    "$integerPart.$decimalPart"
                } else {
                    filteredText
                }
                onValueChange(validText)
            }
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}