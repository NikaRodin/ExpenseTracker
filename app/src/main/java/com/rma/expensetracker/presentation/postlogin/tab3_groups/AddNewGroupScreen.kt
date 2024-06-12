package com.rma.expensetracker.presentation.postlogin.tab3_groups

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.components.buttons.DismissButton
import com.rma.expensetracker.presentation.components.buttons.SaveButton
import com.rma.expensetracker.presentation.components.layouts.TopAppBarLayout
import com.rma.expensetracker.presentation.postlogin.tab1_records.EditRecordInfoContent

@Composable
fun AddNewGroupScreen(
    navController: NavHostController,
    viewModel: AddNewGroupViewModel = viewModel()
){
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

    TopAppBarLayout(
        screenTitle = stringResource(R.string.add_new_record),
        navigationIcons = { DismissButton { viewModel.onDismissClicked(navController) } },
        actions = { SaveButton { viewModel.onSaveClicked(navController) } }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            //Bože sačuvaj haha
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
    }
}