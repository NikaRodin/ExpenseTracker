package com.rma.expensetracker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rma.expensetracker.common.BottomNavBarIndicator
import com.rma.expensetracker.common.CurrentUser
import com.rma.expensetracker.common.LoadingState
import com.rma.expensetracker.data.models.useful.User
import com.rma.expensetracker.presentation.navigation.bottom_navigation.BottomNavItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val isLoading: StateFlow<Boolean> = LoadingState.isLoading

    private val _currentUser: StateFlow<User?> = CurrentUser.currentUser

    private val _userLoggedIn = MutableStateFlow(
        _currentUser
            .map { user -> user != null }
            .stateIn(viewModelScope, SharingStarted.Eagerly, _currentUser.value != null))
    val userLoggedIn: StateFlow<Boolean> = _userLoggedIn.value

    val isBottomNavBarVisible: StateFlow<Boolean> = BottomNavBarIndicator.isBottomNavBarVisible
    val bottomNavItems: StateFlow<List<BottomNavItem>> = BottomNavBarIndicator.bottomNavItems

    val selectedBottomNavItem: StateFlow<BottomNavItem> = BottomNavBarIndicator.selectedBottomNavItem

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
        BottomNavBarIndicator.updateSelectedBottomNavItem(item)
        if(item.direction != BottomNavItem.BottomNavDirection.ADD) {
            BottomNavBarIndicator.updateLastActiveBottomNavItem(item)
        }
    }
}