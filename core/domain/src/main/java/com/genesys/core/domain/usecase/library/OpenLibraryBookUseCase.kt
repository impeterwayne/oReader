package com.genesys.core.domain.usecase.library

import com.genesys.core.domain.repository.library.LibraryRepository
import com.genesys.core.model.library.Book
import com.genesys.core.model.library.OpenBookResult
import javax.inject.Inject

class OpenLibraryBookUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository
) {
    suspend operator fun invoke(book: Book): OpenLibraryBookResult {
        return try {
            OpenLibraryBookResult.Success(libraryRepository.openBook(book))
        } catch (_: Exception) {
            OpenLibraryBookResult.Failure(LibraryUseCaseFailure.Unknown)
        }
    }
}
