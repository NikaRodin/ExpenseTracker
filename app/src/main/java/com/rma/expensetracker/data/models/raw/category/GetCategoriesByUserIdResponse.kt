package com.rma.expensetracker.data.models.raw.category

data class GetCategoriesByUserIdResponse(
    val categories: List<CategoryDto>,
    val message: String,
    val result: String
)