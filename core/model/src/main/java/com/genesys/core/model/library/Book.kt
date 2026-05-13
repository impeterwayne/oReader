package com.genesys.core.model.library

data class Book(
    val id: String,
    val title: String,
    val fileName: String,
    val filePath: String,
    val extension: String,
    val fileSizeBytes: Long,
    val addedAt: Long,
    val locationLabel: String,
    val source: BookSource,
    val lastOpenedAt: Long?,
    val percentComplete: Float?,
    val contentUri: String? = null
)
