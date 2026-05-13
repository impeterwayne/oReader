package com.genesys.core.domain.usecase.library

import com.genesys.core.domain.repository.library.LibraryRepository
import javax.inject.Inject

class RemoveLibraryFolderUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository
) {
    suspend operator fun invoke(folderId: String): RemoveLibraryFolderResult {
        return try {
            val removedFolder = libraryRepository.removeLibraryFolder(folderId)
            val snapshot = libraryRepository.getLibrarySnapshot()
            if (removedFolder != null) {
                RemoveLibraryFolderResult.Removed(removedFolder, snapshot)
            } else {
                RemoveLibraryFolderResult.NotFound(snapshot)
            }
        } catch (_: Exception) {
            RemoveLibraryFolderResult.Failure(LibraryUseCaseFailure.Unknown)
        }
    }
}
