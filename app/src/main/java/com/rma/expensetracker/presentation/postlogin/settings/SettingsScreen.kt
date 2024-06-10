package com.rma.expensetracker.presentation.postlogin.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.components.BackButton
import com.rma.expensetracker.presentation.components.TopAppBarLayout

@Composable
fun SettingsScreen(
    navController: NavHostController,
    viewModel: SettingsViewModel = viewModel()
) {
    TopAppBarLayout(
        screenTitle = stringResource(R.string.settings),
        navigationIcons = { BackButton(navController = navController)},
        actions = {}
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            Row(modifier = Modifier.clickable { viewModel.onLogoutClicked() }) {
                Icon(
                    painter = painterResource(R.drawable.outline_logout_24),
                    contentDescription = stringResource(R.string.logout_icon_label_description),
                    tint = Color.Red
                )
                Text(
                    text = stringResource(R.string.logout),
                    color = Color.Red
                )
            }
        }
    }
}