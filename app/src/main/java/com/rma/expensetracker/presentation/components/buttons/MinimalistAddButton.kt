package com.rma.expensetracker.presentation.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rma.expensetracker.R

@Composable
fun MinimalistAddButton(
    label: @Composable () -> Unit,
    onClick: () -> Unit
) {
    AssistChip(
        onClick = onClick,
        label = label,
        leadingIcon = {
            Icon(
                Icons.Filled.Add,
                contentDescription = stringResource(R.string.add),
                Modifier.size(AssistChipDefaults.IconSize)
            )
        }
    )
}
