package com.rma.expensetracker.presentation.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.components.buttons.DismissButton
import com.rma.expensetracker.presentation.components.input_fields.InputFieldState
import com.rma.expensetracker.presentation.components.other.ColorPicker

@Composable
fun AddCategoryDialog(
    isOpen: Boolean,
    onDismissRequest: () -> Unit,
    onSave: () -> Unit,
    titleState: InputFieldState,
    colorState: Color,
    onColorChange: (Color) -> Unit,
    displayPreview: Boolean = true,
) {
    if (isOpen) {
        Dialog(onDismissRequest = onDismissRequest) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Dismiss button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 25.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.add_category_dialog_title),
                            style = MaterialTheme.typography.titleLarge
                        )
                        DismissButton(onDismissRequest)
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .weight(0.75f)
                                .padding(vertical = 8.dp),
                            value = titleState.text,
                            onValueChange = titleState.onTextChange,
                            label = { Text(stringResource(id = R.string.edit_record_title_field_label)) },
                            placeholder = { Text(stringResource(id = R.string.edit_record_title_placeholder)) }
                        )

                        Spacer(modifier = Modifier.padding(horizontal = 3.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10))
                                    .size(57.dp)
                                    .background(colorState)
                            )
                        }
                    }

                    ColorPicker(
                        displayPreview,
                        colorState,
                        onColorChange
                    )

                    // Save button
                    Button(
                        onClick = { onSave() },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 25.dp)
                    ) {
                        Text(stringResource(R.string.add_button))
                    }
                }
            }
        }
    }
}