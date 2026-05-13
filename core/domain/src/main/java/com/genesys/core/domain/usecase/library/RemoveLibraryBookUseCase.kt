package com.genesys.core.domain.usecase.library

import com.genesys.core.domain.repository.library.LibraryRepository
import javax.inject.Inject

class RemoveLibraryBookUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository
) {
    suspend operator fun invoke(bookId: String): RemoveLibraryBookResult {
        return try {
            val removedBook = libraryRepository.removeBook(bookId)
            val snapshot = libraryRepository.getLibrarySnapshot()
            RemoveLibraryBookResult.Success(removedBook = removedBook, snapshot = snapshot)
        } catch (_: Exception) {
            RemoveLibraryBookResult.Failure(LibraryUseCaseFailure.Unknown)
        }
    }
}
