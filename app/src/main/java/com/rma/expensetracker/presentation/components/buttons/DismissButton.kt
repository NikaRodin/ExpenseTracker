package com.rma.expensetracker.presentation.components.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.rma.expensetracker.R

@Composable
fun DismissButton(onClick: () -> Unit, color: Color = LocalContentColor.current) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Outlined.Close,
            contentDescription = stringResource(R.string.dimiss_button),
            tint = color
        )
    }
}