package com.genesys.core.domain.usecase.library

import com.genesys.core.domain.repository.library.LibraryRepository
import com.genesys.core.model.library.LibrarySnapshot
import javax.inject.Inject

class GetLibrarySnapshotUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository
) {
    suspend operator fun invoke(): GetLibrarySnapshotResult {
        return try {
            GetLibrarySnapshotResult.Success(libraryRepository.getLibrarySnapshot())
        } catch (_: Exception) {
            GetLibrarySnapshotResult.Failure(LibraryUseCaseFailure.Unknown)
        }
    }
}
