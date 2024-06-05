package com.rma.expensetracker.data.models.useful

import androidx.compose.ui.graphics.ImageBitmap
import com.rma.expensetracker.data.models.mock.Category
import com.rma.expensetracker.data.models.mock.User
import java.time.LocalDate

data class Record(
    val id: String,
    val title: String,
    val amount: Float,
    val date: LocalDate,
    val isGroupRecord: Boolean,
    val notes: String? = null,
    val photos: List<ImageBitmap> = emptyList(), //TODO
    val accountId: String,
    val user: User,
    val category: Category,
)

data class SubRecord(
    val id: String,
    val title: String,
    val amount: Float,
    val parentRecordId: String,
    val category: Category,
)