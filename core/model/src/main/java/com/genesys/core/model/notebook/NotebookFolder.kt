package com.genesys.core.model.notebook

import java.util.Date
import java.util.UUID

data class NotebookFolder(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "New Folder",
    val parentFolderId: String? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
