package com.rma.expensetracker.common

import com.rma.expensetracker.data.models.mock.User
import com.rma.expensetracker.presentation.navigation.bottom_navigation.BottomNavBarItems
import com.rma.expensetracker.presentation.navigation.bottom_navigation.BottomNavItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

object BottomNavBarIndicator {
    private val _isBottomNavBarVisible = MutableStateFlow(false)
    val isBottomNavBarVisible: StateFlow<Boolean> = _isBottomNavBarVisible

    private val _bottomNavItems = MutableStateFlow(BottomNavBarItems.BOTTOM_NAV_BAR_ITEMS)
    val bottomNavItems: StateFlow<List<BottomNavItem>> = _bottomNavItems

    private val _selectedBottomNavItem = MutableStateFlow(bottomNavItems.value[0])
    val selectedBottomNavItem: StateFlow<BottomNavItem> = _selectedBottomNavItem

    private val _lastActiveBottomNavItem = MutableStateFlow(selectedBottomNavItem.value)
    val lastActiveBottomNavItem: StateFlow<BottomNavItem> = _lastActiveBottomNavItem

    fun showBottomNavBar() {
        _isBottomNavBarVisible.value = true
    }
    fun hideBottomNavBar() {
        _isBottomNavBarVisible.value = false
    }

    fun setBottomNavItems(items: List<BottomNavItem>) {
        if(items.isNotEmpty()) _bottomNavItems.value = items
    }

    fun updateSelectedBottomNavItem(item: BottomNavItem) {
        _selectedBottomNavItem.update { item }
    }

    fun updateLastActiveBottomNavItem(item: BottomNavItem) {
        _lastActiveBottomNavItem.update { item }
    }

    fun activateLastActiveBottomNavItem() {
        _selectedBottomNavItem.value = _lastActiveBottomNavItem.value
    }
}

object ToastState {
    private val _showToast = MutableStateFlow<String?>(null)
    val showToast: StateFlow<String?>  = _showToast.asStateFlow()

    fun triggerToast(message: String) {
        _showToast.value = message
    }

    fun resetToastToNull() {
        _showToast.value = null
    }
}