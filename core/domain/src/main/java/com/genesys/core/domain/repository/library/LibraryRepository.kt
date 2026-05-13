package com.genesys.core.domain.repository.library

import android.net.Uri
import com.genesys.core.model.library.Book
import com.genesys.core.model.library.LibraryFolder
import com.genesys.core.model.library.LibraryImportResult
import com.genesys.core.model.library.LibrarySnapshot
import com.genesys.core.model.library.OpenBookResult

interface LibraryRepository {
    suspend fun getLibrarySnapshot(): LibrarySnapshot
    suspend fun addLibraryFolder(treeUri: Uri): Boolean
    suspend fun removeLibraryFolder(folderId: String): LibraryFolder?
    suspend fun removeInvalidFolders(): Boolean
    suspend fun importDocuments(uris: List<Uri>): LibraryImportResult
    suspend fun importFile(filePath: String): LibraryImportResult
    suspend fun openBook(book: Book): OpenBookResult
    suspend fun removeBook(bookId: String): Book?
}
