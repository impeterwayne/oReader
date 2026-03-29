package com.genesys.core.model.notebook

import java.util.Date
import java.util.UUID

data class Notebook(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "New notebook",
    val openPageId: String? = null,
    val pageIds: List<String> = emptyList(),
    val parentFolderId: String? = null,
    val defaultBackground: String = "blank",
    val defaultBackgroundType: String = NotebookBackgroundType.Native.key,
    val linkedExternalUri: String? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
) {
    fun getBackgroundType(): NotebookBackgroundType =
        NotebookBackgroundType.fromKey(defaultBackgroundType)

    fun newPage(): NotebookPage =
        NotebookPage(
            notebookId = id,
            background = defaultBackground,
            backgroundType = defaultBackgroundType
        )

    fun getPageIndex(pageId: String): Int = pageIds.indexOf(pageId)
}
