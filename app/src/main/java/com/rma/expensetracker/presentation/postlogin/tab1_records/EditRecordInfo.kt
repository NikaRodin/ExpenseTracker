package com.rma.expensetracker.presentation.postlogin.tab1_records

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.components.ChipsDialog
import com.rma.expensetracker.presentation.components.DateInputField
import com.rma.expensetracker.presentation.components.DropdownComponent
import com.rma.expensetracker.presentation.components.NumericalInputField

@Composable
fun EditRecordInfo(
    viewModel: RecordDetailsViewModel
) {
    val titleState by viewModel.titleState.collectAsState()
    val amountState by viewModel.amountState.collectAsState()
    val dateState by viewModel.dateState.collectAsState()
    val notesState by viewModel.notesState.collectAsState()
    val isTypeMenuExpanded by viewModel.isTypeMenuExpanded.collectAsState()
    val isExpense by viewModel.isExpense.collectAsState()
    val isAccountMenuExpanded by viewModel.isAccountMenuExpanded.collectAsState()
    val selectedAccount by viewModel.selectedAccount.collectAsState()
    val accountsList by viewModel.accountsList.collectAsState()

    val recordTypes: List<String> = listOf(
        stringResource(R.string.expense),
        stringResource(R.string.income)
    )

    LaunchedEffect(true) {
        viewModel.resetEditScreen()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = titleState.text,
            onValueChange = titleState.onTextChange,
            label = { Text(stringResource(id = R.string.edit_record_title_field_label)) },
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DropdownComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f),
                value = if(isExpense) recordTypes[0] else recordTypes[1],
                menuItems = recordTypes,
                isMenuExpanded = isTypeMenuExpanded,
                onArrowClicked = viewModel::onTypeMenuArrowClicked,
                onItemSelected = viewModel::onRecordTypeSelected,
                label = { Text(stringResource(id = R.string.edit_record_type_field_label)) }
            )

            Spacer(modifier = Modifier.padding(horizontal = 6.dp))

            NumericalInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f),
                value = amountState.value,
                onValueChange = { newValue -> viewModel.onAmountChange(newValue) },
                label = { Text(stringResource(id = R.string.edit_record_amount_field_label)) },
                leadingIcon = { Text(if(isExpense) "-" else "+") },
                trailingIcon = { Text("€") }
            )
        }

        DateInputField(
            date = dateState.text,
            onDateSelected = viewModel::onDateChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            label = { Text(stringResource(id = R.string.edit_record_date_field_label)) }
        )

        DropdownComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = if(selectedAccount != -1) accountsList[selectedAccount].title else "",
            menuItems = accountsList.map { acc -> acc.title },
            isMenuExpanded = isAccountMenuExpanded,
            onArrowClicked = viewModel::onAccountMenuArrowClicked,
            onItemSelected = viewModel::onAccountSelected,
            label = { Text(stringResource(id = R.string.edit_record_acc_field_label)) }
        )

        var isDialogOpen by remember { mutableStateOf(false) }
        var savedChips by remember { mutableStateOf(listOf<String>()) }

        Button(onClick = { isDialogOpen = true }) {
            Text("+")
        }

        Text("Saved Chips: ${savedChips.joinToString(", ")}")

        ChipsDialog(
            isOpen = isDialogOpen,
            onDismissRequest = { isDialogOpen = false },
            onSave = { newChips -> savedChips = newChips }
        )


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = notesState.text,
            minLines = 3,
            maxLines = 5,
            onValueChange = notesState.onTextChange,
            label = { Text(stringResource(id = R.string.edit_record_notes_field_label)) },
        )
    }
}
