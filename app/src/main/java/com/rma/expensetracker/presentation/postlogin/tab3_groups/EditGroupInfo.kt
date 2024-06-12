package com.rma.expensetracker.presentation.postlogin.tab3_groups

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.components.buttons.DismissButton
import com.rma.expensetracker.presentation.components.buttons.MinimalistAddButton
import com.rma.expensetracker.presentation.components.dialogs.PopUpDialog
import com.rma.expensetracker.presentation.postlogin.tab1_records.DataText
import com.rma.expensetracker.presentation.postlogin.tab1_records.SimpleDataRow

@Composable
fun EditGroupInfo(
    viewModel: GroupDetailsViewModel
) {
    val titleState by viewModel.titleState.collectAsState()
    val usersOnEditScreen by viewModel.usersOnEditScreen.collectAsState()
    val selectedUsersList by viewModel.selectedUsersList.collectAsState()
    val allUsersList by viewModel.allUsersList.collectAsState()
    val isAddUserDialogOpen by viewModel.isAddUserDialogOpen.collectAsState()
    val isRemovePersonDialogOpen by viewModel.isRemovePersonDialogOpen.collectAsState()

    LaunchedEffect(true) {
        viewModel.resetEditScreen()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = titleState.text,
            onValueChange = titleState.onTextChange,
            label = { Text(stringResource(id = R.string.edit_group_acc_title_field_label)) },
            placeholder = { Text(stringResource(id = R.string.edit_group_acc_title_placeholder)) }
        )

        Spacer(modifier = Modifier.padding(4.dp))

        SimpleDataRow {
            DataText(text = stringResource(R.string.people) + " (${usersOnEditScreen.size})")
        }

        Divider(
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.padding(2.dp))

        SimpleDataRow {
            MinimalistAddButton(
                label =  { Text(stringResource(R.string.add_person)) },
                onClick = viewModel::onAddPersonClicked
            )
        }
        /*TODO add person pop up dialog */

        LazyColumn {
            items(usersOnEditScreen) { user ->
                PersonRow(user) {
                    DismissButton {
                        if (usersOnEditScreen.size > 1)
                            viewModel.onRemovePersonClicked(user.id)
                        else
                            viewModel.showWarningToast(
                                "Nemoguće izbrisati korisnika. U grupi mora ostati barem jedna osoba."
                            )
                    }
                }
            }
        }

        PopUpDialog(
            message = stringResource(R.string.are_you_sure_you_want_to_remove_user) ,
            dismissButtonText = stringResource(R.string.cancel),
            confirmButtonText = stringResource(R.string.confirm),
            isOpen = isRemovePersonDialogOpen,
            onDismiss = viewModel::onRemovePersonDismissed,
            onConfirm = viewModel::onRemovePersonConfirmed
        )
    }
}
