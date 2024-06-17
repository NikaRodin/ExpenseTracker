package com.rma.expensetracker.data.models.raw.category

data class GetCategoryByIdResponse(
    val category: CategoryDto,
    val message: String,
    val result: String
)

data class CategoryDto(
    val color: String,
    val title: String,
    val uniqueID: String,
    val userID: String
)