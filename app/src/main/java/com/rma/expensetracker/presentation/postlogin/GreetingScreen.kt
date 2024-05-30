package com.rma.expensetracker.presentation.postlogin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun GreetingScreen(viewModel: PostLoginViewModel = PostLoginViewModel()){
    val currentUser by viewModel.currentUser.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = currentUser.toString())
    }
}