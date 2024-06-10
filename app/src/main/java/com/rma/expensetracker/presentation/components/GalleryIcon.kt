package com.rma.expensetracker.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.rma.expensetracker.R
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations

@Composable
fun GalleryIcon(navController: NavHostController) {
    IconButton(
        onClick = {
            navController.navigate(PostLoginDestinations.GalleryScreen.destination) {
                launchSingleTop = true
            }
        }
    ) {
        Icon(
            painter = painterResource(R.drawable.outline_photo_library_24),
            contentDescription = stringResource(R.string.gallery_icon_description)
        )
    }
}