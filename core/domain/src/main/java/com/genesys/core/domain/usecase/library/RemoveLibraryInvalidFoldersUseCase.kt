package com.genesys.core.domain.usecase.library

import com.genesys.core.domain.repository.library.LibraryRepository
import javax.inject.Inject

class RemoveLibraryInvalidFoldersUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository
) {
    suspend operator fun invoke(): RemoveInvalidFoldersResult {
        return try {
            val removed = libraryRepository.removeInvalidFolders()
            val snapshot = libraryRepository.getLibrarySnapshot()
            RemoveInvalidFoldersResult.Success(removed = removed, snapshot = snapshot)
        } catch (_: Exception) {
            RemoveInvalidFoldersResult.Failure(LibraryUseCaseFailure.Unknown)
        }
    }
}
