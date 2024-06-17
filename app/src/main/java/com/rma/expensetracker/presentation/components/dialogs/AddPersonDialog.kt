package com.rma.expensetracker.presentation.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.rma.expensetracker.data.models.useful.User
import com.rma.expensetracker.presentation.components.buttons.DismissButton
import com.rma.expensetracker.presentation.components.input_fields.InputFieldState
import com.rma.expensetracker.presentation.components.other.PersonPicker

@Composable
fun AddPersonDialog(
    isOpen: Boolean,
    onDismissRequest: () -> Unit,
    onSave: () -> Unit,
    selectedUsers: List<User>,
    searchQueryState: InputFieldState,
    onUserSelected: (User) -> Unit,
    onUserDeselected: (User) -> Unit,
    getFilteredUsers: () -> List<User>
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
                            text = stringResource(R.string.add_person_dialog_title),
                            style = MaterialTheme.typography.titleLarge
                        )
                        DismissButton(onDismissRequest)
                    }

                    PersonPicker(
                        selectedUsers = selectedUsers,
                        searchQueryState = searchQueryState,
                        onUserSelected = onUserSelected,
                        onUserDeselected = onUserDeselected,
                        getFilteredUsers = getFilteredUsers
                    )

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