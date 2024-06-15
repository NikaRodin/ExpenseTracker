package com.rma.expensetracker.presentation.postlogin.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.components.buttons.BackButton
import com.rma.expensetracker.presentation.components.cards.ClickableCard
import com.rma.expensetracker.presentation.components.layouts.TopAppBarLayout

@Composable
fun SettingsScreen(
    navController: NavHostController,
    viewModel: SettingsViewModel = viewModel()
) {
    TopAppBarLayout(
        screenTitle = stringResource(R.string.settings),
        navigationIcons = {
            BackButton(navController = navController, showBottomNavBar = true)
        },
        actions = {}
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
                .padding(8.dp)
        ) {
            ClickableCard(
                onClick = { viewModel.onCategoriesClicked(navController) },
                mainContent = {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(R.string.categories_icon_label),
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(stringResource(R.string.my_categories))
                    }
                }
            )

            Row(
                modifier = Modifier
                    .clickable { viewModel.onLogoutClicked() }
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_logout_24),
                    contentDescription = stringResource(R.string.logout_icon_label_description),
                    tint = Color.Red
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = stringResource(R.string.logout),
                    color = Color.Red
                )
            }
        }
    }
}