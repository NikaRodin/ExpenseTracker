package com.rma.expensetracker.data.mock_database

import androidx.compose.ui.graphics.Color
import com.rma.expensetracker.data.models.mock.Category

object MockCategoryDatabase {
    private var categoriesList: MutableList<Category> = mutableListOf(
        Category(
            id = "stipendijaAnne",
            title = "obrazovanje",
            color = Color.Red,
            userId = "anneId"
        ),
        Category(
            id = "posaoAnne",
            title = "posao",
            color = Color(230,190, 40),
            userId = "anneId"
        ),
        Category(
            id = "autoAnne",
            title = "auto",
            color = Color.Green,
            userId = "anneId"
        ),
        Category(
            id = "domAnne",
            title = "dom",
            color = Color.Magenta,
            userId = "anneId"
        ),
        Category(
            id = "odmorAnne",
            title = "odmor",
            color = Color.Cyan,
            userId = "anneId"
        ),
        Category(
            id = "kucniLjubimacAnne",
            title = "kućni ljubimac",
            color = Color.Gray,
            userId = "anneId"
        ),
        Category(
            id = "hranaAnne",
            title = "hrana",
            color = Color(255,146, 30),
            userId = "anneId"
        ),

        //////

        Category(
            id = "posaoPeter",
            title = "posao",
            color = Color.Cyan,
            userId = "peterId"
        ),
        Category(
            id = "putovanjePeter",
            title = "putovanje",
            color = Color.Magenta,
            userId = "peterId"
        ),
        Category(
            id = "zabavaPeter",
            title = "zabava",
            color = Color(255,146, 30),
            userId = "peterId"
        ),

        /////

        Category(
            id = "kućaRobert",
            title = "kuća",
            color = Color.Red,
            userId = "robertId"
        ),
        Category(
            id = "posaoRobert",
            title = "posao",
            color = Color.Blue,
            userId = "robertId"
        ),
        Category(
            id = "dijeteRobert",
            title = "dijete",
            color = Color.Green,
            userId = "robertId"
        )
    )

    fun getCategoryById(categoryId: String): Category {
        return categoriesList.first { it.id == categoryId}
    }
    fun getCategoriesByUserId(userId: String): List<Category> {
        return categoriesList.filter { it.userId == userId}
    }

    fun addCategory(category: Category) {
        categoriesList.add(category)
    }

    fun deleteCategory(categoryId: String) {
        categoriesList.removeIf { it.id == categoryId }
    }
}