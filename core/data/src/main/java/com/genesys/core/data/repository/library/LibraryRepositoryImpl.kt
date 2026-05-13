package com.genesys.core.data.repository.library

import android.net.Uri
import com.genesys.core.domain.repository.library.LibraryBookOpener
import com.genesys.core.domain.repository.library.LibraryRepository
import com.genesys.core.domain.repository.library.LibrarySnapshotLoader
import com.genesys.core.model.library.Book
import com.genesys.core.model.library.BookSource
import com.genesys.core.model.library.LibraryFolder
import com.genesys.core.model.library.LibraryImportResult
import com.genesys.core.model.library.LibrarySnapshot
import com.genesys.core.model.library.OpenBookResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LibraryRepositoryImpl @Inject internal constructor(
    private val snapshotLoader: LibrarySnapshotLoader,
    private val folderManager: LibraryFolderManager,
    private val importer: LibraryImporter,
    private val bookOpener: LibraryBookOpener,
    private val storage: LibraryStorage
) : LibraryRepository {

    override suspend fun getLibrarySnapshot(): LibrarySnapshot = withContext(Dispatchers.IO) {
        snapshotLoader.getLibrarySnapshot()
    }

    override suspend fun addLibraryFolder(treeUri: Uri): Boolean = withContext(Dispatchers.IO) {
        folderManager.addLibraryFolder(treeUri)
    }

    override suspend fun removeLibraryFolder(folderId: String): LibraryFolder? = withContext(Dispatchers.IO) {
        folderManager.removeLibraryFolder(folderId)
    }

    override suspend fun removeInvalidFolders(): Boolean = withContext(Dispatchers.IO) {
        folderManager.removeInvalidFolders()
    }

    override suspend fun importDocuments(uris: List<Uri>): LibraryImportResult = withContext(Dispatchers.IO) {
        importer.importDocuments(uris)
    }

    override suspend fun importFile(filePath: String): LibraryImportResult = withContext(Dispatchers.IO) {
        importer.importFile(filePath)
    }

    override suspend fun openBook(book: Book): OpenBookResult = withContext(Dispatchers.IO) {
        bookOpener.openBook(book)
    }

    override suspend fun removeBook(bookId: String): Book? = withContext(Dispatchers.IO) {
        val records = readIndexCompat().toMutableList()
        val record = records.firstOrNull { it.id == bookId } ?: return@withContext null

        File(record.filePath).delete()
        records.remove(record)
        writeIndexCompat(records)

        Book(
            id = record.id,
            title = record.title,
            fileName = record.fileName,
            filePath = record.filePath,
            extension = record.extension,
            fileSizeBytes = record.fileSizeBytes,
            addedAt = record.importedAt,
            locationLabel = "Managed library",
            source = BookSource.ManagedCopy,
            lastOpenedAt = null,
            percentComplete = null
        )
    }

    private fun readIndexCompat(): List<StoredBook> = storage.readIndex()

    private fun writeIndexCompat(records: List<StoredBook>) {
        storage.writeIndex(records)
    }
}

