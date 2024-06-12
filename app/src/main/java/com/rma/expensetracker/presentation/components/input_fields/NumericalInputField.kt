package com.rma.expensetracker.presentation.components.input_fields

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
    OutlinedTextField(
        value = value,
        label = label,
        onValueChange = {
            val numDecPoints = it.count { char -> char == '.' }
            if (numDecPoints <= 1) {
                val filteredText = it.filter { char -> char.isDigit() || char == '.' }

                var integerPart = filteredText
                var decimalPart = ""
                val decimalIndex = filteredText.indexOf('.')

                if (decimalIndex >= 0) {
                    decimalPart = filteredText.substring(decimalIndex + 1)
                    integerPart = filteredText.substring(0, decimalIndex)
                }

                if(integerPart.length <= 8 && decimalPart.length <= 2) {
                    var validText = filteredText
                    if (decimalIndex >= 0)
                        validText = "$integerPart.$decimalPart"
                    onValueChange(validText)
                }
            }
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}