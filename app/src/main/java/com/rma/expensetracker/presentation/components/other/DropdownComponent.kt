package com.rma.expensetracker.presentation.components.other

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.toSize
import com.rma.expensetracker.R

@Composable
fun DropdownComponent(
    value: String,
    menuItems: List<String>,
    modifier: Modifier = Modifier,
    isMenuExpanded: Boolean = false,
    onArrowClicked: () -> Unit,
    onItemSelected: (Int) -> Unit,
    label:  @Composable (() -> Unit)? = null
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            enabled = false,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onArrowClicked() }
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = label,
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
                disabledLabelColor = MaterialTheme.colorScheme.onSurface.copy(ContentAlpha.medium),
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.onSurface.copy(ContentAlpha.medium)
            ),
            trailingIcon = {
                Icon(
                    imageVector = if (isMenuExpanded) Icons.Filled.KeyboardArrowUp
                                  else Icons.Filled.KeyboardArrowDown,
                    contentDescription = stringResource(id = R.string.expand_collapse_arrow_label)
                )
            }
        )

        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { onArrowClicked() },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            menuItems.forEachIndexed { index, text ->
                DropdownMenuItem(
                    text = { Text(text = text) },
                    onClick = { onItemSelected(index) }
                )
            }
        }
    }
}