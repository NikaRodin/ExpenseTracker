package com.rma.expensetracker.data.models.useful

import androidx.compose.ui.graphics.ImageBitmap
import java.time.LocalDate

data class Record(
    val id: String,
    val title: String,
    val amount: Double,
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