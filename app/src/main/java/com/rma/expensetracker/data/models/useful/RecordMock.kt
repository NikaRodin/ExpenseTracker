package com.rma.expensetracker.data.models.useful

import androidx.compose.ui.graphics.ImageBitmap
import java.time.LocalDate

data class RecordMock(
    val id: String,
    val title: String,
    val amount: Double,
    val date: LocalDate,
    val isGroupRecord: Boolean,
    val notes: String? = null,
    val photos: List<ImageBitmap> = emptyList(), //TODO
    val userId: String,
    val accountId: String,
    val categoryId: String,
)

data class SubRecordMock(
    val id: String,
    val title: String,
    val amount: Double,
    val parentRecordId: String,
    val categoryId: String,
)