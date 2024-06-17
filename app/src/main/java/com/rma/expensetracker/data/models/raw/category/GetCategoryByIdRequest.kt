package com.rma.expensetracker.data.models.raw.category

data class GetCategoryByIdRequest(
    val operation: String,
    val category: CategoryID
)