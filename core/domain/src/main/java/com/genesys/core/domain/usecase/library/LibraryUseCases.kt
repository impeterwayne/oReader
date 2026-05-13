package com.genesys.core.domain.usecase.library

import javax.inject.Inject

data class LibraryUseCases @Inject constructor(
    val addLibraryFolderUseCase: AddLibraryFolderUseCase,
    val getLibrarySnapshotUseCase: GetLibrarySnapshotUseCase,
    val importLibraryDocumentsUseCase: ImportLibraryDocumentsUseCase,
    val importLibraryFileUseCase: ImportLibraryFileUseCase,
    val openLibraryBookUseCase: OpenLibraryBookUseCase,
    val removeLibraryBookUseCase: RemoveLibraryBookUseCase,
    val removeLibraryFolderUseCase: RemoveLibraryFolderUseCase,
    val removeLibraryInvalidFoldersUseCase: RemoveLibraryInvalidFoldersUseCase
)