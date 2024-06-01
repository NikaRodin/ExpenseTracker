package com.rma.expensetracker.presentation.postlogin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddNewRecordScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Add New Record")
    }
}