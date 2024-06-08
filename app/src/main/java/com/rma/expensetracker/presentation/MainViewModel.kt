package com.rma.expensetracker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.data.models.mock.User
import com.rma.expensetracker.presentation.navigation.bottom_navigation.BottomNavBarItems
import com.rma.expensetracker.presentation.navigation.bottom_navigation.BottomNavItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _currentUser: StateFlow<User?> = CurrentUser.currentUser
    private val _userLoggedIn = MutableStateFlow(
        _currentUser
            .map { user -> user != null }
            .stateIn(viewModelScope, SharingStarted.Eagerly, _currentUser.value != null))
    val userLoggedIn: StateFlow<Boolean> = _userLoggedIn.value

    val isBottomNavBarVisible: StateFlow<Boolean> = BottomNavBarIndicator.isBottomNavBarVisible
    val bottomNavItems: StateFlow<List<BottomNavItem>> = BottomNavBarIndicator.bottomNavItems

    private val _selectedBottomNavItem = MutableStateFlow(bottomNavItems.value[0])
    val selectedBottomNavItem: StateFlow<BottomNavItem?> = _selectedBottomNavItem

    init {
        viewModelScope.launch {
            userLoggedIn.collect { loggedIn ->
                if(loggedIn) {
                    BottomNavBarIndicator.showBottomNavBar()
                }
            }
        }
    }

    fun onBottomNavElementClicked(item: BottomNavItem) {
        _selectedBottomNavItem.update { item }
    }
}