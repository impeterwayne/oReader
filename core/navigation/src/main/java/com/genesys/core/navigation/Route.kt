package com.genesys.core.navigation

import android.os.Parcelable
import androidx.navigation3.runtime.NavKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed interface Route : NavKey, Parcelable {
    @Serializable
    @Parcelize
    data object Reader : Route

    @Serializable
    @Parcelize
    data object LibrarySettings : Route

    @Serializable
    @Parcelize
    data object Notebook : Route

    @Serializable
    @Parcelize
    data class NotebookEditor(
        val pageId: String,
        val bookId: String? = null
    ) : Route

    @Serializable
    @Parcelize
    data class NotebookPages(
        val bookId: String
    ) : Route
}
