package com.genesys.feature.library

import com.genesys.core.model.library.*
import com.genesys.core.domain.repository.library.*

import android.net.Uri
import com.genesys.core.common.base.mvi.Action
import com.genesys.core.common.base.mvi.SideEffect
import com.genesys.core.common.base.mvi.UiState

data class LibraryUiState(
    val isLoading: Boolean = true,
    val books: List<Book> = emptyList(),
    val selectedFolders: List<LibraryFolder> = emptyList(),
    val invalidFolders: List<LibraryFolder> = emptyList(),
    val selectedPageIndex: Int = 0,
    val pageSize: Int = 4
) : UiState {
    val totalPages: Int
        get() = if (books.isEmpty()) 0 else ((books.size - 1) / pageSize) + 1

    val currentPageBooks: List<Book>
        get() {
            if (books.isEmpty()) return emptyList()
            val safePageIndex = selectedPageIndex.coerceIn(0, totalPages - 1)
            val startIndex = safePageIndex * pageSize
            val endIndex = (startIndex + pageSize).coerceAtMost(books.size)
            return books.subList(startIndex, endIndex)
        }
}

sealed interface LibraryAction : Action {
    data object LoadLibrary : LibraryAction
    data object RefreshLibrary : LibraryAction
    data object LibraryChangedExternally : LibraryAction
    data class AddLibraryFolder(val folderUri: Uri) : LibraryAction
    data class RemoveLibraryFolder(val folderId: String) : LibraryAction
    data object RemoveInvalidFolders : LibraryAction
    data class SelectPage(val pageIndex: Int) : LibraryAction
    data class OpenBook(val book: Book) : LibraryAction
    data class RemoveBook(val bookId: String) : LibraryAction
}

sealed interface LibrarySideEffect : SideEffect {
    data class ShowMessage(val message: String) : LibrarySideEffect
    data class OpenBook(val filePath: String) : LibrarySideEffect
}
