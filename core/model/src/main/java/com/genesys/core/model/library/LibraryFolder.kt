package com.genesys.core.model.library

data class LibraryFolder(
    val id: String,
    val treeUri: String,
    val displayName: String,
    val isValid: Boolean,
    val invalidReason: String? = null
)
