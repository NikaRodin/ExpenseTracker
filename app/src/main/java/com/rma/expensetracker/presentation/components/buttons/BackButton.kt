package com.rma.expensetracker.presentation.components.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.common.BottomNavBarIndicator

@Composable
fun BackButton(
    navController: NavHostController,
    showBottomNavBar: Boolean = false
) {
    IconButton(
        onClick = {
            if(showBottomNavBar)
                BottomNavBarIndicator.showBottomNavBar()
            navController.popBackStack()
        }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_button)
        )
    }
}