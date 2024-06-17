package com.rma.expensetracker.data.mock_database

import androidx.compose.ui.graphics.Color
import com.rma.expensetracker.data.models.useful.Category

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
            color = Color.Yellow,
            userId = "anneId"
        ),
        Category(
            id = "izlazakAnne",
            title = "izlazak",
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
            id = "zabavaAnne",
            title = "zabava",
            color = Color(255,146, 30),
            userId = "anneId"
        ),

        //////

        Category(
            id = "posaoPeter",
            title = "posao",
            color = Color.Green,
            userId = "peterId"
        ),
        Category(
            id = "restoranPeter",
            title = "restoran",
            color = Color.Yellow,
            userId = "peterId"
        ),
        Category(
            id = "zabavaPeter",
            title = "zabava",
            color = Color.Blue,
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