package com.rma.expensetracker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rma.expensetracker.data.remote.models.User
import com.rma.expensetracker.presentation.MainActivity.Companion.currentUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainViewModel() : ViewModel() {
    private val _currentUser: StateFlow<User?> = currentUserRepository.currentUser
    private val _userLoggedIn = MutableStateFlow(
        _currentUser
            .map { user -> user != null }
            .stateIn(viewModelScope, SharingStarted.Eagerly, _currentUser.value != null))
    val userLoggedIn: StateFlow<Boolean> = _userLoggedIn.value
}