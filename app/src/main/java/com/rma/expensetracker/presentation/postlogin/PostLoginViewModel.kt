package com.rma.expensetracker.presentation.postlogin

import androidx.lifecycle.ViewModel
import com.rma.expensetracker.data.remote.models.User
import com.rma.expensetracker.presentation.MainActivity
import kotlinx.coroutines.flow.StateFlow

class PostLoginViewModel : ViewModel() {
    val currentUser: StateFlow<User?> = MainActivity.currentUserRepository.currentUser
}