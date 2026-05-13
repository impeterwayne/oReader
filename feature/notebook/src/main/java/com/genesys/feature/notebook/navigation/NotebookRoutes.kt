package com.genesys.feature.notebook.navigation

import com.genesys.core.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data object NotebookLibrary : Route

@Serializable
data class NotebookEditor(
    val pageId: String,
    val bookId: String? = null
) : Route

@Serializable
data class NotebookPages(
    val bookId: String
) : Route
