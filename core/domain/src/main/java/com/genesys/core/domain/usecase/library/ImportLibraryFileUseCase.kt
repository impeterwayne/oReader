package com.genesys.core.domain.usecase.library

import android.net.Uri
import com.genesys.core.domain.repository.library.LibraryRepository
import com.genesys.core.model.library.Book
import com.genesys.core.model.library.LibraryFolder
import com.genesys.core.model.library.LibraryImportResult
import com.genesys.core.model.library.LibrarySnapshot
import com.genesys.core.model.library.OpenBookResult
import javax.inject.Inject

class ImportLibraryFileUseCase @Inject constructor(
    private val libraryRepository: LibraryRepository
) {
    suspend operator fun invoke(filePath: String): LibraryImportResult {
        return libraryRepository.importFile(filePath)
    }
}
