package com.rma.expensetracker.data.models.raw.category

data class DeleteCategoryRequest(
    val operation: String,
    val category: CategoryID
)
data class CategoryID(
    val categoryID: String
)