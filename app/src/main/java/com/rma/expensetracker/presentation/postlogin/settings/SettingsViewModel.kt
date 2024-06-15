package com.rma.expensetracker.presentation.postlogin.settings

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.presentation.navigation.directions.PostLoginDestinations

class SettingsViewModel : ViewModel() {

    init {
        BottomNavBarIndicator.hideBottomNavBar()
    }
    fun onLogoutClicked() {
        CurrentUser.updateCurrentUser(null)
        BottomNavBarIndicator.hideBottomNavBar()
    }

    fun onCategoriesClicked(navController: NavHostController) {
        navController.navigate(
            PostLoginDestinations.CategoriesScreen.destination
        ){
            launchSingleTop = true
        }
    }
}