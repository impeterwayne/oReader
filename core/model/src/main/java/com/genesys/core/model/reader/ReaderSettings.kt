package com.genesys.core.model.reader

data class ReaderSettings(
    val version: Int = 1,
    val scanFolders: List<ReaderScanFolder> = emptyList()
) {
    companion object {
        const val KV_KEY = "READER_SETTINGS"
    }
}

data class ReaderScanFolder(
    val uri: String,
    val label: String = "",
    val addedAt: Long = 0L,
    val updatedAt: Long = addedAt
)
