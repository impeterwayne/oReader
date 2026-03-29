package com.genesys.core.model.notebook

import java.util.Date
import java.util.UUID

data class NotebookStroke(
    val id: String = UUID.randomUUID().toString(),
    val size: Float,
    val pen: NotebookPen,
    val color: Int = 0xFF000000.toInt(),
    val maxPressure: Int = 4096,
    val top: Float,
    val bottom: Float,
    val left: Float,
    val right: Float,
    val points: List<NotebookStrokePoint>,
    val pageId: String,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
