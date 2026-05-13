package com.genesys.codebase.reader

internal data class StoredBook(
    val id: String,
    val title: String,
    val fileName: String,
    val filePath: String,
    val extension: String,
    val fileSizeBytes: Long,
    val importedAt: Long
)

internal data class StoredFolder(
    val id: String,
    val treeUri: String,
    val displayName: String,
    val selectedAt: Long
)
