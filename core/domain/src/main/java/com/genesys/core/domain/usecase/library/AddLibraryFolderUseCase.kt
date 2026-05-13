package com.genesys.core.domain.usecase.library

import android.net.Uri
import com.genesys.core.domain.repository.library.LibraryRepository
import javax.inject.Inject

class AddLibraryFolderUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository
) {
    suspend operator fun invoke(treeUri: Uri): AddLibraryFolderResult {
        return try {
            val added = libraryRepository.addLibraryFolder(treeUri)
            val snapshot = libraryRepository.getLibrarySnapshot()
            if (added) {
                AddLibraryFolderResult.Added(snapshot)
            } else {
                AddLibraryFolderResult.AlreadyAdded(snapshot)
            }
        } catch (_: Exception) {
            AddLibraryFolderResult.Failure(LibraryUseCaseFailure.Unknown)
        }
    }
}
