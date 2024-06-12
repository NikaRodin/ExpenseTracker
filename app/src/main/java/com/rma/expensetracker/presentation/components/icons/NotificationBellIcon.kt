package com.rma.expensetracker.presentation.components.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.rma.expensetracker.R

@Composable
fun NotificationBellIcon(
    navController: NavHostController
) {
    IconButton(
        onClick = {
            /*navController.navigate(PostLoginDestinations.NotificationsScreen.destination) {
                launchSingleTop = true
            }*/
        }
    ) {
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = stringResource(R.string.notifications_icon_description)
        )
    }
}