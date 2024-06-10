package com.rma.expensetracker.presentation.postlogin.settings

import androidx.lifecycle.ViewModel
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.common.CurrentUser

class SettingsViewModel : ViewModel() {

    init {
        BottomNavBarIndicator.hideBottomNavBar()
    }
    fun onLogoutClicked() {
        CurrentUser.updateCurrentUser(null)
        BottomNavBarIndicator.hideBottomNavBar()
    }
}