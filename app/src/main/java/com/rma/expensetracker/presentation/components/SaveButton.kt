package com.rma.expensetracker.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.rma.expensetracker.R

@Composable
fun SaveButton(onClick: () -> Unit) {
    var isEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(isEnabled) {
        if (!isEnabled) {
            kotlinx.coroutines.delay(500)
            isEnabled = true
        }
    }

    IconButton(onClick = {
        if (isEnabled) {
            isEnabled = false
            onClick()
        }
    }, enabled = isEnabled) {
        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = stringResource(R.string.save_button)
        )
    }
}