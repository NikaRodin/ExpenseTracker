package com.rma.expensetracker.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations

@Composable
fun SettingsIcon(navController: NavHostController) {
    IconButton(
        onClick = {
            navController.navigate(PostLoginDestinations.SettingsScreen.destination) {
                launchSingleTop = true
            }
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = stringResource(R.string.settings_icon_description)
        )
    }
}