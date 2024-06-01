package com.rma.expensetracker.common

import com.rma.expensetracker.data.models.useful.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object CurrentUser {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun updateCurrentUser(user: User?) {
        _currentUser.value = user
    }
}