package com.rma.expensetracker.presentation.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rma.expensetracker.R
import com.rma.expensetracker.data.models.useful.Category
import com.rma.expensetracker.presentation.components.buttons.DismissButton
import com.rma.expensetracker.presentation.components.other.CategoryTag

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryPickDialog(
    categories: List<Category>,
    isOpen: Boolean,
    selectedCategoryId: String?,
    onCategorySelected: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onSave: () -> Unit
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
                        modifier = Modifier.fillMaxWidth().padding(bottom = 25.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.pick_category),
                            style = MaterialTheme.typography.titleLarge
                        )
                        DismissButton(onDismissRequest)
                    }

                    // FlowRow of chips
                    FlowRow(
                        horizontalArrangement  = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        categories.forEach { category ->
                            Box(modifier = Modifier.padding(6.dp)) {
                                CategoryTag(
                                    category = category,
                                    textStyle = MaterialTheme.typography.labelMedium,
                                    isSelected = category.id == selectedCategoryId,
                                    onClick = onCategorySelected
                                )
                            }
                        }
                    }

                    // Save button
                    Button(
                        onClick = {
                            onSave()
                            onDismissRequest()
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 25.dp)
                    ) {
                        Text(stringResource(R.string.save))
                    }
                }
            }
        }
    }
}