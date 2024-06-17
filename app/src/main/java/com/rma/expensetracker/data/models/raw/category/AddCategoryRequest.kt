package com.rma.expensetracker.data.models.raw.category

data class AddCategoryRequest(
    val operation: String,
    val category: CategoryToAdd
)

data class CategoryToAdd(
    val color: String,
    val title: String,
    val userID: String
)