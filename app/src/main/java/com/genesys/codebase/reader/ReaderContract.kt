package com.genesys.codebase.reader

import android.net.Uri
import com.genesys.core.common.base.mvi.Action
import com.genesys.core.common.base.mvi.SideEffect
import com.genesys.core.common.base.mvi.UiState

data class ReaderUiState(
    val isLoading: Boolean = true,
    val books: List<ReaderBook> = emptyList(),
    val selectedFolders: List<ReaderLibraryFolder> = emptyList(),
    val invalidFolders: List<ReaderLibraryFolder> = emptyList()
) : UiState

sealed interface ReaderAction : Action {
    data object LoadLibrary : ReaderAction
    data object RefreshLibrary : ReaderAction
    data object LibraryChangedExternally : ReaderAction
    data class AddLibraryFolder(val folderUri: Uri) : ReaderAction
    data class RemoveLibraryFolder(val folderId: String) : ReaderAction
    data object RemoveInvalidFolders : ReaderAction
    data class OpenBook(val book: ReaderBook) : ReaderAction
    data class RemoveBook(val bookId: String) : ReaderAction
}

sealed interface ReaderSideEffect : SideEffect {
    data class ShowMessage(val message: String) : ReaderSideEffect
    data class OpenBook(val filePath: String) : ReaderSideEffect
}
