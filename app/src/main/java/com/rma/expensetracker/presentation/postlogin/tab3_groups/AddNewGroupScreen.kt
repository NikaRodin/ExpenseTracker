package com.rma.expensetracker.presentation.postlogin.tab3_groups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.components.buttons.DismissButton
import com.rma.expensetracker.presentation.components.buttons.SaveButton
import com.rma.expensetracker.presentation.components.input_fields.NumericalInputField
import com.rma.expensetracker.presentation.components.layouts.TopAppBarLayout
import com.rma.expensetracker.presentation.components.other.DropdownComponent
import com.rma.expensetracker.presentation.components.other.PersonPicker
import com.rma.expensetracker.presentation.postlogin.tab1_records.DataText

@Composable
fun AddNewGroupScreen(
    navController: NavHostController,
    viewModel: AddNewGroupViewModel = viewModel()
){
    val titleState by viewModel.titleState.collectAsState()
    val amountState by viewModel.amountState.collectAsState()
    val searchQueryState by viewModel.searchQueryState.collectAsState()
    val isTypeMenuExpanded by viewModel.isTypeMenuExpanded.collectAsState()
    val isExpense by viewModel.isExpense.collectAsState()
    val selectedUsersList by viewModel.selectedUsersList.collectAsState()

    val recordTypes: List<String> = listOf(
        stringResource(R.string.expense),
        stringResource(R.string.income)
    )

    TopAppBarLayout(
        screenTitle = stringResource(R.string.add_new_group_account),
        navigationIcons = { DismissButton({ viewModel.onDismissClicked(navController) }) },
        actions = { SaveButton { viewModel.onSaveClicked(navController) } }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            Column(
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
                    placeholder = { Text(stringResource(id = R.string.edit_record_title_placeholder)) }
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
                        label = { Text(stringResource(id = R.string.edit_initial_balance_field_label)) },
                        leadingIcon = { Text(if(isExpense) "-" else "+") },
                        trailingIcon = { Text("€") }
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 6.dp))

                DataText(text = stringResource(R.string.people))

                val screenHeight = LocalConfiguration.current.screenHeightDp.dp
                Column(
                    modifier = Modifier.height(screenHeight).fillMaxWidth()
                ) {
                    PersonPicker(
                        selectedUsers = selectedUsersList,
                        searchQueryState = searchQueryState,
                        onUserSelected = viewModel::onUserSelected,
                        onUserDeselected = viewModel::onUserDeselected,
                        getFilteredUsers = viewModel::getFilteredUsers
                    )
                }
            }
        }
    }
}