package com.rma.expensetracker.presentation.postlogin.tab1_records

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.components.buttons.BackButton
import com.rma.expensetracker.presentation.components.buttons.DeleteButton
import com.rma.expensetracker.presentation.components.buttons.DismissButton
import com.rma.expensetracker.presentation.components.buttons.EditButton
import com.rma.expensetracker.presentation.components.buttons.SaveButton
import com.rma.expensetracker.presentation.components.layouts.TopAppBarLayout

@Composable
fun RecordDetailsScreen(
    navController: NavHostController,
    viewModel: RecordDetailsViewModel = viewModel()
) {
    val currentRecord by viewModel.currentRecord.collectAsState()
    val currentAccount by viewModel.currentAccount.collectAsState()
    val isEditModeOn by viewModel.isEditModeOn.collectAsState()

    TopAppBarLayout(
        screenTitle = if(!isEditModeOn) stringResource(R.string.record_preview)
                      else stringResource(R.string.record_edit),
        navigationIcons = {
            if(!isEditModeOn)
                BackButton(navController = navController, showBottomNavBar = true)
            else
                DismissButton(viewModel::onDismissClicked)
        }, actions = {
            if(!isEditModeOn) {
                EditButton(viewModel::onEditClicked)
                DeleteButton(viewModel::onTrashcanClicked)
            } else {
                SaveButton { viewModel.onSaveClicked() }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            if(currentRecord == null)
                Text(text = stringResource(R.string.record_not_selected))
            else if (isEditModeOn)
                EditRecordInfo(viewModel, false)
            else
                PreviewRecordInfo(
                    currentRecord!!,
                    currentAccount,
                    viewModel,
                    navController
                )
        }
    }
}