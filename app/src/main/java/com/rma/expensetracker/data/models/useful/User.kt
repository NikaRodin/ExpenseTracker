package com.rma.expensetracker.data.models.useful

import androidx.compose.ui.graphics.ImageBitmap

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val userName: String? = null,
    val profilePicture: ImageBitmap? = null //TODO
)