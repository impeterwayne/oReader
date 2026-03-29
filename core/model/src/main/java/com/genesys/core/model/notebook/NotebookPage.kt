package com.genesys.core.model.notebook

import java.util.Date
import java.util.UUID

data class NotebookPage(
    val id: String = UUID.randomUUID().toString(),
    val scroll: Int = 0,
    val notebookId: String? = null,
    val background: String = "blank",
    val backgroundType: String = NotebookBackgroundType.Native.key,
    val parentFolderId: String? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
) {
    fun getBackgroundType(): NotebookBackgroundType =
        NotebookBackgroundType.fromKey(backgroundType)
}
