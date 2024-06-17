package com.rma.expensetracker.data.models.mock

import androidx.compose.ui.graphics.Color
import com.rma.expensetracker.R

data class Category(
    val id: String,
    val title: String,
        val color: Color = Color.Red,
    val userId: String
) {
    companion object {
        val GROUP_RECORD = R.string.group_record_category_title
    }
}