package com.rma.expensetracker.data.interactors

import androidx.compose.ui.graphics.toArgb
import com.rma.expensetracker.common.Constants
import com.rma.expensetracker.data.models.mapCategoryDtoListToCategoryList
import com.rma.expensetracker.data.models.mapCategoryDtoToCategory
import com.rma.expensetracker.data.models.raw.account.UserID
import com.rma.expensetracker.data.models.raw.category.AddCategoryRequest
import com.rma.expensetracker.data.models.raw.category.CategoryID
import com.rma.expensetracker.data.models.raw.category.CategoryToAdd
import com.rma.expensetracker.data.models.raw.category.DeleteCategoryRequest
import com.rma.expensetracker.data.models.raw.category.GetCategoriesByUserIdRequest
import com.rma.expensetracker.data.models.raw.category.GetCategoryByIdRequest
import com.rma.expensetracker.data.models.useful.Category

object CategoryInteractor {
    suspend fun getCategoryById(categoryId: String): Category? {
        val request = GetCategoryByIdRequest(
            operation = "get_category_by_id",
            category = CategoryID(categoryId)
        )

        try {
            val response = Constants.apiService.getCategoryById(request)
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    mapCategoryDtoToCategory(responseBody.category)
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                    null
                }
            } else {
                println("Error: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
            return null
        }
    }
    suspend fun getCategoriesByUserId(userId: String): List<Category> {
        val finalCategories = mutableListOf<Category>()

        val request = GetCategoriesByUserIdRequest(
            operation = "get_categories_for_user_id",
            user = UserID(userId)
        )

        try {
            val response = Constants.apiService.getCategoriesByUserId(request)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    finalCategories.addAll(
                        mapCategoryDtoListToCategoryList(responseBody.categories)
                    )
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                }
            } else {
                println("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
        }

        return finalCategories
    }

    suspend fun addCategory(newCategory: Category): Boolean {
        val request = AddCategoryRequest(
            operation = "create_new_category",
            category = CategoryToAdd(
                color = newCategory.color.toArgb().toString(),
                title = newCategory.title,
                userID = newCategory.userId
            )
        )

        try {
            val response = Constants.apiService.addCategory(request)
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    true
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                    false
                }
            } else {
                println("Error: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
            return false
        }
    }

    suspend fun deleteCategory(categoryId: String): Boolean {
        val request = DeleteCategoryRequest(
            operation = "delete_category",
            category = CategoryID(categoryId)
        )

        try {
            val response = Constants.apiService.deleteCategory(request)
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null && responseBody.result == "success") {
                    true
                } else {
                    println("Error: ${responseBody?.message ?: "Unknown error"}")
                    false
                }
            } else {
                println("Error: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            println("Failure: ${e.message}")
            return false
        }
    }

    suspend fun findCategory(userId: String, title: String): Category? {
        return getCategoriesByUserId(userId).find { it.title.lowercase() == title.lowercase()}
    }
}