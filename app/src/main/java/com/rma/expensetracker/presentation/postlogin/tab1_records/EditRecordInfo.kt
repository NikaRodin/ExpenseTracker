package com.rma.expensetracker.presentation.postlogin.tab1_records

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rma.expensetracker.R
import com.rma.expensetracker.data.models.useful.Account
import com.rma.expensetracker.data.models.useful.Category
import com.rma.expensetracker.presentation.components.buttons.MinimalistAddButton
import com.rma.expensetracker.presentation.components.dialogs.CategoryPickDialog
import com.rma.expensetracker.presentation.components.input_fields.DateInputField
import com.rma.expensetracker.presentation.components.input_fields.InputFieldState
import com.rma.expensetracker.presentation.components.input_fields.NumericalInputField
import com.rma.expensetracker.presentation.components.input_fields.NumericalInputFieldState
import com.rma.expensetracker.presentation.components.other.CategoryTag
import com.rma.expensetracker.presentation.components.other.DropdownComponent

@Composable
fun EditRecordInfo(
    viewModel: RecordDetailsViewModel,
    showAccountPicker: Boolean = true
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
    val categoriesList by viewModel.categoriesList.collectAsState()
    val category by viewModel.category.collectAsState()
    val selectedCategoryId by viewModel.selectedCategoryId.collectAsState()
    val isCategoryPickerOpen by viewModel.isCategoryPickerOpen.collectAsState()

    LaunchedEffect(true) {
        viewModel.resetEditScreen()
    }

    EditRecordInfoContent(
        titleState,
        amountState,
        dateState,
        notesState,
        isTypeMenuExpanded,
        isExpense,
        isAccountMenuExpanded,
        selectedAccount,
        accountsList,
        categoriesList,
        category,
        selectedCategoryId,
        isCategoryPickerOpen,
        showAccountPicker,
        viewModel::onCategorySelected,
        viewModel::closeCategoryPicker,
        viewModel::onCategorySaved,
        viewModel::openCategoryPicker,
        viewModel::onAccountMenuArrowClicked,
        viewModel::onAccountSelected,
        viewModel::onDateChange,
        viewModel::onTypeMenuArrowClicked,
        viewModel::onRecordTypeSelected,
        viewModel::onAmountChange
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EditRecordInfoContent(
    titleState: InputFieldState,
    amountState: NumericalInputFieldState,
    dateState: InputFieldState,
    notesState: InputFieldState,
    isTypeMenuExpanded: Boolean,
    isExpense: Boolean,
    isAccountMenuExpanded: Boolean,
    selectedAccount: Int,
    accountsList: List<Account>,
    categoriesList: List<Category>,
    category: Category?,
    selectedCategoryId: String?,
    isCategoryPickerOpen: Boolean,
    showAccountPicker: Boolean,
    onCategorySelected: (String) -> Unit,
    closeCategoryPicker: () -> Unit,
    onCategorySaved: () -> Unit,
    openCategoryPicker: (String?) -> Unit,
    onAccountMenuArrowClicked: () -> Unit,
    onAccountSelected: (Int) -> Unit,
    onDateChange: (String) -> Unit,
    onTypeMenuArrowClicked: () -> Unit,
    onRecordTypeSelected: (Int) -> Unit,
    onAmountChange: (String) -> Unit
) {
    val recordTypes: List<String> = listOf(
        stringResource(R.string.expense),
        stringResource(R.string.income)
    )

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
                onArrowClicked = onTypeMenuArrowClicked,
                onItemSelected = onRecordTypeSelected,
                label = { Text(stringResource(id = R.string.edit_record_type_field_label)) }
            )

            Spacer(modifier = Modifier.padding(horizontal = 6.dp))

            NumericalInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f),
                value = amountState.value,
                onValueChange = { newValue -> onAmountChange(newValue) },
                label = { Text(stringResource(id = R.string.edit_record_amount_field_label)) },
                leadingIcon = { Text(if(isExpense) "-" else "+") },
                trailingIcon = { Text("€") }
            )
        }

        DateInputField(
            date = dateState.text,
            onDateSelected = onDateChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            label = { Text(stringResource(id = R.string.edit_record_date_field_label)) },
        )

        if(showAccountPicker) {
            DropdownComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = if(selectedAccount != -1) accountsList[selectedAccount].title else "",
                menuItems = accountsList.map { acc -> acc.title },
                isMenuExpanded = isAccountMenuExpanded,
                onArrowClicked = onAccountMenuArrowClicked,
                onItemSelected = onAccountSelected,
                label = { Text(stringResource(id = R.string.edit_record_acc_field_label)) },
            )
        }

        SimpleDataColumn {
            DataText(text = stringResource(R.string.categories) + ":")
            if(category != null) {
                FlowRow(modifier = Modifier.padding(8.dp)) {
                    CategoryTag(
                        category = category,
                        onClick = openCategoryPicker,
                        trailingIcon = { Icon(
                            Icons.Outlined.Edit,
                            contentDescription = stringResource(R.string.edit_category),
                        )}
                    )
                }
            } else {
                MinimalistAddButton(
                    onClick = { openCategoryPicker(null) },
                    label = { Text(stringResource(R.string.add_category_to_record)) }
                )
            }
        }

        CategoryPickDialog(
            categories = categoriesList,
            isOpen = isCategoryPickerOpen,
            selectedCategoryId = selectedCategoryId,
            onCategorySelected = onCategorySelected,
            onDismissRequest = closeCategoryPicker,
            onSave = onCategorySaved
        )

        SimpleDataColumn {
            DataText(text = stringResource(R.string.notes) + ":")
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = notesState.text,
                minLines = 3,
                maxLines = 5,
                onValueChange = notesState.onTextChange,
                //label = { Text(stringResource(id = R.string.edit_record_notes_field_label)) },
                placeholder = { Text(stringResource(id = R.string.edit_record_notes_placeholder)) }
            )
        }
    }
}
