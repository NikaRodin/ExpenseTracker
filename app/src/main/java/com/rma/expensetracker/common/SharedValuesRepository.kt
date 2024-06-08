package com.rma.expensetracker.common

import com.rma.expensetracker.data.models.mock.User
import com.rma.expensetracker.presentation.navigation.bottom_navigation.BottomNavBarItems
import com.rma.expensetracker.presentation.navigation.bottom_navigation.BottomNavItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object CurrentUser {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun updateCurrentUser(user: User?) {
        _currentUser.value = user
    }
}

object SelectedRecordId {
    private val _selectedRecordId = MutableStateFlow<String?>(null)
    val selectedRecordId: StateFlow<String?> = _selectedRecordId

    fun updateSelectedRecordId(recordId: String?) {
        _selectedRecordId.value = recordId
    }
}

object BottomNavBarIndicator{
    private val _isBottomNavBarVisible = MutableStateFlow(false)
    val isBottomNavBarVisible: StateFlow<Boolean> = _isBottomNavBarVisible

    private val _bottomNavItems = MutableStateFlow(BottomNavBarItems.BOTTOM_NAV_BAR_ITEMS)
    val bottomNavItems: StateFlow<List<BottomNavItem>> = _bottomNavItems

    fun showBottomNavBar() {
        _isBottomNavBarVisible.value = true
    }
    fun hideBottomNavBar() {
        _isBottomNavBarVisible.value = false
    }

    fun setBottomNavItems(items: List<BottomNavItem>) {
        if(items.isNotEmpty()) _bottomNavItems.value = items
    }
}