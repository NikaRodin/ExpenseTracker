package com.rma.expensetracker.presentation.postlogin.tab3_groups

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
import com.rma.expensetracker.presentation.components.buttons.DismissButton
import com.rma.expensetracker.presentation.components.buttons.EditButton
import com.rma.expensetracker.presentation.components.buttons.SaveButton
import com.rma.expensetracker.presentation.components.layouts.TopAppBarLayout

@Composable
fun GroupDetailsScreen(
    navController: NavHostController,
    viewModel: GroupDetailsViewModel = viewModel()
) {
    val currentGroupAccount by viewModel.currentGroupAccount.collectAsState()
    val groupUsersList by viewModel.groupUsersList.collectAsState()
    val isEditModeOn by viewModel.isEditModeOn.collectAsState()

    TopAppBarLayout(
        screenTitle = if(!isEditModeOn && currentGroupAccount != null){
            currentGroupAccount!!.title
        } else if (currentGroupAccount == null){
            stringResource(R.string.group_preview)
        } else {
            stringResource(R.string.group_edit)
        },
        navigationIcons = {
            if(!isEditModeOn)
                BackButton(navController = navController, showBottomNavBar = true)
            else
                DismissButton(viewModel::onDismissClicked)
        }, actions = {
            if(!isEditModeOn) {
                EditButton(viewModel::onEditClicked)
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
            if(currentGroupAccount == null)
                Text(text = stringResource(R.string.group_not_selected))
            else if (isEditModeOn)
                EditGroupInfo(viewModel)
            else
                PreviewGroupInfo(
                    currentGroupAccount!!,
                    groupUsersList,
                    viewModel,
                    navController
                )
        }
    }
}