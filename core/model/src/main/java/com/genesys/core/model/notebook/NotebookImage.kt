package com.genesys.core.model.notebook

import java.util.Date
import java.util.UUID

data class NotebookImage(
    val id: String = UUID.randomUUID().toString(),
    val x: Int = 0,
    val y: Int = 0,
    val height: Int,
    val width: Int,
    val uri: String? = null,
    val pageId: String,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
