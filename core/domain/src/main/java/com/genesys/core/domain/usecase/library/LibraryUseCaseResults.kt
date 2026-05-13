package com.genesys.core.domain.usecase.library

import com.genesys.core.model.library.Book
import com.genesys.core.model.library.LibraryFolder
import com.genesys.core.model.library.LibrarySnapshot
import com.genesys.core.model.library.OpenBookResult

sealed interface LibraryUseCaseFailure {
    data object Unknown : LibraryUseCaseFailure
}

sealed interface GetLibrarySnapshotResult {
    data class Success(val snapshot: LibrarySnapshot) : GetLibrarySnapshotResult
    data class Failure(val reason: LibraryUseCaseFailure) : GetLibrarySnapshotResult
}

sealed interface AddLibraryFolderResult {
    data class Added(val snapshot: LibrarySnapshot) : AddLibraryFolderResult
    data class AlreadyAdded(val snapshot: LibrarySnapshot) : AddLibraryFolderResult
    data class Failure(val reason: LibraryUseCaseFailure) : AddLibraryFolderResult
}

sealed interface RemoveLibraryFolderResult {
    data class Removed(
        val folder: LibraryFolder,
        val snapshot: LibrarySnapshot
    ) : RemoveLibraryFolderResult

    data class NotFound(val snapshot: LibrarySnapshot) : RemoveLibraryFolderResult
    data class Failure(val reason: LibraryUseCaseFailure) : RemoveLibraryFolderResult
}

sealed interface RemoveInvalidFoldersResult {
    data class Success(
        val removed: Boolean,
        val snapshot: LibrarySnapshot
    ) : RemoveInvalidFoldersResult

    data class Failure(val reason: LibraryUseCaseFailure) : RemoveInvalidFoldersResult
}

sealed interface RemoveLibraryBookResult {
    data class Success(
        val removedBook: Book?,
        val snapshot: LibrarySnapshot
    ) : RemoveLibraryBookResult

    data class Failure(val reason: LibraryUseCaseFailure) : RemoveLibraryBookResult
}

sealed interface OpenLibraryBookResult {
    data class Success(val result: OpenBookResult) : OpenLibraryBookResult
    data class Failure(val reason: LibraryUseCaseFailure) : OpenLibraryBookResult
}
