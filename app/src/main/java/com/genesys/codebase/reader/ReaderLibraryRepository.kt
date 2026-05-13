package com.genesys.codebase.reader

import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

enum class ReaderBookSource {
    SafFolder,
    ManagedCopy
}

data class ReaderBook(
    val id: String,
    val title: String,
    val fileName: String,
    val filePath: String,
    val extension: String,
    val fileSizeBytes: Long,
    val addedAt: Long,
    val locationLabel: String,
    val source: ReaderBookSource,
    val lastOpenedAt: Long?,
    val percentComplete: Float?,
    val contentUri: String? = null
)

data class ReaderLibraryFolder(
    val id: String,
    val treeUri: String,
    val displayName: String,
    val isValid: Boolean,
    val invalidReason: String? = null
)

data class ReaderLibrarySnapshot(
    val books: List<ReaderBook>,
    val selectedFolders: List<ReaderLibraryFolder>,
    val invalidFolders: List<ReaderLibraryFolder>
)

@Singleton
class ReaderLibraryRepository @Inject internal constructor(
    private val snapshotLoader: ReaderLibrarySnapshotLoader,
    private val folderManager: ReaderLibraryFolderManager,
    private val importer: ReaderLibraryImporter,
    private val bookOpener: ReaderBookOpener,
    private val storage: ReaderLibraryStorage
) {

    data class ImportResult(
        val importedCount: Int,
        val skippedUnsupported: List<String>,
        val failed: List<String>
    )

    sealed class OpenBookResult {
        data class Available(val filePath: String, val isStaged: Boolean) : OpenBookResult()
        data class Unavailable(val reason: String) : OpenBookResult()
    }

    suspend fun getLibrarySnapshot(): ReaderLibrarySnapshot = withContext(Dispatchers.IO) {
        snapshotLoader.getLibrarySnapshot()
    }

    suspend fun addLibraryFolder(treeUri: Uri): Boolean = withContext(Dispatchers.IO) {
        folderManager.addLibraryFolder(treeUri)
    }

    suspend fun removeLibraryFolder(folderId: String): ReaderLibraryFolder? = withContext(Dispatchers.IO) {
        folderManager.removeLibraryFolder(folderId)
    }

    suspend fun removeInvalidFolders(): Boolean = withContext(Dispatchers.IO) {
        folderManager.removeInvalidFolders()
    }

    suspend fun importDocuments(uris: List<Uri>): ImportResult = withContext(Dispatchers.IO) {
        importer.importDocuments(uris)
    }

    suspend fun importFile(filePath: String): ImportResult = withContext(Dispatchers.IO) {
        importer.importFile(filePath)
    }

    suspend fun openBook(book: ReaderBook): OpenBookResult = withContext(Dispatchers.IO) {
        bookOpener.openBook(book)
    }

    suspend fun removeBook(bookId: String): ReaderBook? = withContext(Dispatchers.IO) {
        val records = readIndexCompat().toMutableList()
        val record = records.firstOrNull { it.id == bookId } ?: return@withContext null

        File(record.filePath).delete()
        records.remove(record)
        writeIndexCompat(records)

        ReaderBook(
            id = record.id,
            title = record.title,
            fileName = record.fileName,
            filePath = record.filePath,
            extension = record.extension,
            fileSizeBytes = record.fileSizeBytes,
            addedAt = record.importedAt,
            locationLabel = "Managed library",
            source = ReaderBookSource.ManagedCopy,
            lastOpenedAt = null,
            percentComplete = null
        )
    }

    private fun readIndexCompat(): List<StoredBook> = storage.readIndex()

    private fun writeIndexCompat(records: List<StoredBook>) {
        storage.writeIndex(records)
    }

    companion object {
        val SUPPORTED_EXTENSIONS = setOf(
            "epub", "pdf", "fb2", "mobi", "azw", "azw3", "djvu", "djv", "cbz", "txt", "rtf", "htm", "html"
        )

        val SUPPORTED_MIME_TYPES = arrayOf(
            "application/epub+zip",
            "application/pdf",
            "application/x-fictionbook+xml",
            "application/x-mobipocket-ebook",
            "application/vnd.amazon.ebook",
            "image/vnd.djvu",
            "application/vnd.comicbook+zip",
            "application/x-cbz",
            "text/plain",
            "text/html",
            "application/rtf",
            "application/octet-stream"
        )
    }
}
