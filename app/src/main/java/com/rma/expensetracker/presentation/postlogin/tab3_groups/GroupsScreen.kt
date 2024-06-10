package com.rma.expensetracker.presentation.postlogin.tab3_groups

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.rma.expensetracker.common.BottomNavBarIndicator

@Composable
fun GroupsScreen(){
    LaunchedEffect(true) {
        BottomNavBarIndicator.showBottomNavBar()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Groups")
    }
}