package com.rma.expensetracker.presentation.postlogin.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.components.buttons.BackButton
import com.rma.expensetracker.presentation.components.buttons.MinimalistAddButton
import com.rma.expensetracker.presentation.components.dialogs.AddCategoryDialog
import com.rma.expensetracker.presentation.components.dialogs.PopUpDialog
import com.rma.expensetracker.presentation.components.layouts.TopAppBarLayout
import com.rma.expensetracker.presentation.components.other.CategoryTag

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoriesScreen(
    navController: NavHostController,
    viewModel: CategoriesViewModel = viewModel()
) {
    val categoriesList by viewModel.categoriesList.collectAsState()
    val unusedCategories by viewModel.unusedCategories.collectAsState()
    val isDeleteDialogOpen by viewModel.isDeleteDialogOpen.collectAsState()
    val isAddDialogOpen by viewModel.isAddDialogOpen.collectAsState()
    val titleState by viewModel.titleState.collectAsState()
    val colorState by viewModel.selectedColor.collectAsState()

    LaunchedEffect(true) {
        viewModel.resetCategoriesScreen()
    }

    TopAppBarLayout(
        screenTitle = stringResource(R.string.my_categories),
        navigationIcons = {
            BackButton(navController = navController)
        }, actions = {}
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
                .padding(8.dp)
        ) {
            FlowRow(
                horizontalArrangement  = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                categoriesList.forEach { category ->
                    Box(modifier = Modifier.padding(6.dp)) {
                        CategoryTag(
                            category = category,
                            textStyle = MaterialTheme.typography.labelMedium,
                            isSelected = false,
                            onClick = {
                                if(unusedCategories.contains(category)) {
                                    viewModel.onDeletableCategoryClicked(category.id)
                                }else{
                                    viewModel.onUsedCategoryClicked()
                                }
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = stringResource(R.string.dimiss_button)
                                )
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            MinimalistAddButton(
                onClick = viewModel::onAddClicked,
                label = { Text(stringResource(R.string.add_category_to_record)) }
            )

            PopUpDialog(
                message = stringResource(R.string.are_you_sure_you_want_to_delete_category),
                dismissButtonText = stringResource(R.string.cancel),
                confirmButtonText = stringResource(R.string.delete),
                isOpen = isDeleteDialogOpen,
                onDismiss = viewModel::onDeleteDismissed,
                onConfirm = viewModel::onDeleteConfirmed
            )

            AddCategoryDialog(
                isOpen = isAddDialogOpen,
                titleState = titleState,
                colorState = colorState,
                onDismissRequest = viewModel::onAddDismissed,
                onSave = viewModel::onAddCategorySaved,
                onColorChange = viewModel::onColorChange,
                displayPreview = false
            )
        }
    }
}