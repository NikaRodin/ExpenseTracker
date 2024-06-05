package com.rma.expensetracker.data.interactors

import com.rma.expensetracker.data.mock_database.MockCategoryDatabase
import com.rma.expensetracker.data.models.mock.Category

object CategoryInteractor {
    fun getCategoryById(categoryId: String): Category {
        return MockCategoryDatabase.getCategoryById(categoryId)
    }
    fun getCategoriesByUserId(userId: String): List<Category> {
        return MockCategoryDatabase.getCategoriesByUserId(userId)
    }
}