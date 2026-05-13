package com.genesys.core.model.library

data class LibraryImportResult(
    val importedCount: Int,
    val skippedUnsupported: List<String>,
    val failed: List<String>
)
