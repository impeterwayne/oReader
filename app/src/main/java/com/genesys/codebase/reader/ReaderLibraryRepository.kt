package com.genesys.codebase.reader

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.OpenableColumns
import androidx.core.content.ContextCompat
import com.genesys.feature.koreader.bridge.KoreaderReadingStateBridge
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber
import java.io.File
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

enum class ReaderBookSource {
    Device,
    ManagedCopy
}

enum class ReaderStorageAccessRequirement {
    None,
    ReadExternalStorage,
    ManageAllFiles
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
    val percentComplete: Float?
)

data class ReaderLibrarySnapshot(
    val books: List<ReaderBook>,
    val storageAccessRequirement: ReaderStorageAccessRequirement
)

@Singleton
class ReaderLibraryRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val readingStateBridge: KoreaderReadingStateBridge
) {

    data class ImportResult(
        val importedCount: Int,
        val skippedUnsupported: List<String>,
        val failed: List<String>
    )

    suspend fun getLibrarySnapshot(): ReaderLibrarySnapshot = withContext(Dispatchers.IO) {
        val readingStateByPath = readingStateBridge
            .getRecentDocuments(context, limit = 500)
            .associateBy { it.filePath }

        var indexChanged = false
        val managedRecords = readIndex().filter { record ->
            val exists = File(record.filePath).exists()
            if (!exists) {
                indexChanged = true
            }
            exists
        }

        if (indexChanged) {
            writeIndex(managedRecords)
        }

        val storageAccessRequirement = storageAccessRequirement()
        val managedBooks = managedRecords.map { record ->
            val readingState = readingStateByPath[record.filePath]
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
                lastOpenedAt = readingState?.lastOpenedTimestamp,
                percentComplete = readingState?.percentComplete
            )
        }
        val deviceBooks = if (storageAccessRequirement == ReaderStorageAccessRequirement.None) {
            scanDeviceBooks(readingStateByPath)
        } else {
            emptyList()
        }

        ReaderLibrarySnapshot(
            books = (managedBooks + deviceBooks)
                .distinctBy { it.filePath }
                .sortedWith(
                    compareByDescending<ReaderBook> { it.lastOpenedAt ?: 0L }
                        .thenByDescending { it.addedAt }
                        .thenBy { it.title.lowercase() }
                ),
            storageAccessRequirement = storageAccessRequirement
        )
    }

    fun hasSharedStorageAccess(): Boolean {
        return storageAccessRequirement() == ReaderStorageAccessRequirement.None
    }

    suspend fun importDocuments(uris: List<Uri>): ImportResult = withContext(Dispatchers.IO) {
        ensureStorage()

        val records = readIndex().toMutableList()
        var importedCount = 0
        val skippedUnsupported = mutableListOf<String>()
        val failed = mutableListOf<String>()

        uris.forEachIndexed { index, uri ->
            val displayName = resolveDisplayName(uri)
                ?.takeIf { it.isNotBlank() }
                ?: "book-${System.currentTimeMillis()}-$index"

            val extension = displayName.substringAfterLast('.', "").lowercase()
            if (extension !in SUPPORTED_EXTENSIONS) {
                skippedUnsupported += displayName
                return@forEachIndexed
            }

            val targetFile = createUniqueTargetFile(displayName)
            try {
                val copied = context.contentResolver.openInputStream(uri)?.use { input ->
                    targetFile.outputStream().buffered().use { output ->
                        input.copyTo(output)
                    }
                    true
                } ?: false

                if (!copied) {
                    failed += displayName
                    return@forEachIndexed
                }

                records += createStoredBook(targetFile, displayName, extension)
                importedCount += 1
            } catch (error: Exception) {
                targetFile.delete()
                failed += displayName
                Timber.e(error, "Failed to import reader document: %s", displayName)
            }
        }

        writeIndex(records)
        ImportResult(
            importedCount = importedCount,
            skippedUnsupported = skippedUnsupported,
            failed = failed
        )
    }

    suspend fun importFile(filePath: String): ImportResult = withContext(Dispatchers.IO) {
        ensureStorage()

        val sourceFile = File(filePath)
        val displayName = sourceFile.name.ifBlank {
            "book-${System.currentTimeMillis()}"
        }
        val extension = displayName.substringAfterLast('.', "").lowercase()

        if (!sourceFile.exists() || !sourceFile.isFile) {
            return@withContext ImportResult(
                importedCount = 0,
                skippedUnsupported = emptyList(),
                failed = listOf(displayName)
            )
        }

        if (extension !in SUPPORTED_EXTENSIONS) {
            return@withContext ImportResult(
                importedCount = 0,
                skippedUnsupported = listOf(displayName),
                failed = emptyList()
            )
        }

        val records = readIndex().toMutableList()
        val targetFile = createUniqueTargetFile(displayName)

        try {
            sourceFile.inputStream().buffered().use { input ->
                targetFile.outputStream().buffered().use { output ->
                    input.copyTo(output)
                }
            }

            records += createStoredBook(targetFile, displayName, extension)
            writeIndex(records)

            ImportResult(
                importedCount = 1,
                skippedUnsupported = emptyList(),
                failed = emptyList()
            )
        } catch (error: Exception) {
            targetFile.delete()
            Timber.e(error, "Failed to import reader file path: %s", filePath)
            ImportResult(
                importedCount = 0,
                skippedUnsupported = emptyList(),
                failed = listOf(displayName)
            )
        }
    }

    suspend fun removeBook(bookId: String): ReaderBook? = withContext(Dispatchers.IO) {
        val records = readIndex().toMutableList()
        val record = records.firstOrNull { it.id == bookId } ?: return@withContext null

        File(record.filePath).delete()
        records.remove(record)
        writeIndex(records)

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

    private fun scanDeviceBooks(
        readingStateByPath: Map<String, KoreaderReadingStateBridge.DocumentState>
    ): List<ReaderBook> {
        val books = mutableListOf<ReaderBook>()
        val seenPaths = mutableSetOf<String>()

        resolveScanRoots().forEach { root ->
            root.walkTopDown()
                .onEnter { directory ->
                    directory.canRead() && !shouldSkipDirectory(directory)
                }
                .onFail { file, error ->
                    Timber.w(error, "Reader library scan could not enter %s", file.absolutePath)
                }
                .forEach { file ->
                    if (!file.isFile) {
                        return@forEach
                    }

                    val extension = file.extension.lowercase()
                    if (extension !in SUPPORTED_EXTENSIONS) {
                        return@forEach
                    }

                    val canonicalPath = canonicalPath(file)
                    if (!seenPaths.add(canonicalPath)) {
                        return@forEach
                    }

                    val readingState = readingStateByPath[canonicalPath]
                        ?: readingStateByPath[file.absolutePath]
                    books += ReaderBook(
                        id = canonicalPath,
                        title = file.nameWithoutExtension.ifBlank { file.name },
                        fileName = file.name,
                        filePath = canonicalPath,
                        extension = extension,
                        fileSizeBytes = file.length(),
                        addedAt = file.lastModified(),
                        locationLabel = file.parent ?: root.absolutePath,
                        source = ReaderBookSource.Device,
                        lastOpenedAt = readingState?.lastOpenedTimestamp,
                        percentComplete = readingState?.percentComplete
                    )
                }
        }

        Timber.i("Reader library scan found %d device books", books.size)
        return books
    }

    private fun resolveScanRoots(): List<File> {
        val candidates = mutableListOf<File>()

        @Suppress("DEPRECATION")
        Environment.getExternalStorageDirectory()
            ?.takeIf { it.exists() && it.canRead() }
            ?.let(candidates::add)

        context.getExternalFilesDirs(null)
            .filterNotNull()
            .mapNotNull { directory ->
                val rootPath = directory.absolutePath.substringBefore(
                    delimiter = "/Android/",
                    missingDelimiterValue = directory.absolutePath
                )
                rootPath.takeIf { it.isNotBlank() }?.let(::File)
            }
            .filter { it.exists() && it.canRead() }
            .forEach(candidates::add)

        return candidates.distinctBy(::canonicalPath)
    }

    private fun shouldSkipDirectory(directory: File): Boolean {
        if (directory.name in EXCLUDED_SCAN_DIRECTORIES) {
            return true
        }

        val path = directory.absolutePath
        return path.contains("/Android/data/") || path.contains("/Android/obb/")
    }

    private fun storageAccessRequirement(): ReaderStorageAccessRequirement {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                ReaderStorageAccessRequirement.None
            } else {
                ReaderStorageAccessRequirement.ManageAllFiles
            }
        } else {
            val hasReadPermission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            if (hasReadPermission) {
                ReaderStorageAccessRequirement.None
            } else {
                ReaderStorageAccessRequirement.ReadExternalStorage
            }
        }
    }

    private fun resolveDisplayName(uri: Uri): String? {
        return try {
            context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                if (!cursor.moveToFirst()) {
                    return@use null
                }
                val columnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (columnIndex >= 0) cursor.getString(columnIndex) else null
            }
        } catch (error: Exception) {
            Timber.w(error, "Unable to resolve display name for reader document: %s", uri)
            null
        }
    }

    private fun createStoredBook(targetFile: File, originalName: String, extension: String): StoredBook {
        return StoredBook(
            id = UUID.randomUUID().toString(),
            title = originalName.substringBeforeLast('.', originalName),
            fileName = targetFile.name,
            filePath = targetFile.absolutePath,
            extension = extension,
            fileSizeBytes = targetFile.length(),
            importedAt = System.currentTimeMillis()
        )
    }

    private fun createUniqueTargetFile(originalName: String): File {
        ensureStorage()

        val sanitizedName = sanitizeFileName(originalName)
        val title = sanitizedName.substringBeforeLast('.', sanitizedName)
        val extension = sanitizedName.substringAfterLast('.', "")

        var candidate = if (extension.isNotBlank()) {
            File(booksDirectory, "$title.$extension")
        } else {
            File(booksDirectory, title)
        }

        var duplicateIndex = 2
        while (candidate.exists()) {
            candidate = if (extension.isNotBlank()) {
                File(booksDirectory, "$title ($duplicateIndex).$extension")
            } else {
                File(booksDirectory, "$title ($duplicateIndex)")
            }
            duplicateIndex += 1
        }

        return candidate
    }

    private fun sanitizeFileName(fileName: String): String {
        val cleaned = fileName
            .replace(INVALID_FILE_NAME_CHARS, "_")
            .trim()

        return if (cleaned.isBlank()) {
            "book-${System.currentTimeMillis()}"
        } else {
            cleaned
        }
    }

    private fun ensureStorage() {
        rootDirectory.mkdirs()
        booksDirectory.mkdirs()
        indexFile.parentFile?.mkdirs()
    }

    private fun readIndex(): List<StoredBook> {
        if (!indexFile.exists()) {
            return emptyList()
        }

        return try {
            val entries = JSONArray(indexFile.readText())
            buildList(entries.length()) {
                repeat(entries.length()) { index ->
                    val item = entries.optJSONObject(index) ?: return@repeat
                    add(
                        StoredBook(
                            id = item.optString(KEY_ID),
                            title = item.optString(KEY_TITLE),
                            fileName = item.optString(KEY_FILE_NAME),
                            filePath = item.optString(KEY_FILE_PATH),
                            extension = item.optString(KEY_EXTENSION),
                            fileSizeBytes = item.optLong(KEY_FILE_SIZE_BYTES),
                            importedAt = item.optLong(KEY_IMPORTED_AT)
                        )
                    )
                }
            }.filter {
                it.id.isNotBlank() && it.title.isNotBlank() && it.filePath.isNotBlank()
            }
        } catch (error: Exception) {
            Timber.e(error, "Failed to parse reader library index")
            emptyList()
        }
    }

    private fun writeIndex(records: List<StoredBook>) {
        ensureStorage()

        val payload = JSONArray().apply {
            records.forEach { record ->
                put(
                    JSONObject()
                        .put(KEY_ID, record.id)
                        .put(KEY_TITLE, record.title)
                        .put(KEY_FILE_NAME, record.fileName)
                        .put(KEY_FILE_PATH, record.filePath)
                        .put(KEY_EXTENSION, record.extension)
                        .put(KEY_FILE_SIZE_BYTES, record.fileSizeBytes)
                        .put(KEY_IMPORTED_AT, record.importedAt)
                )
            }
        }

        indexFile.writeText(payload.toString())
    }

    private fun canonicalPath(file: File): String {
        return runCatching { file.canonicalPath }.getOrElse { file.absolutePath }
    }

    private data class StoredBook(
        val id: String,
        val title: String,
        val fileName: String,
        val filePath: String,
        val extension: String,
        val fileSizeBytes: Long,
        val importedAt: Long
    )

    companion object {
        private val INVALID_FILE_NAME_CHARS = Regex("""[\\/:*?"<>|]""")
        private val EXCLUDED_SCAN_DIRECTORIES = setOf(
            "Android",
            ".thumbnails",
            "LOST.DIR"
        )

        private const val KEY_ID = "id"
        private const val KEY_TITLE = "title"
        private const val KEY_FILE_NAME = "fileName"
        private const val KEY_FILE_PATH = "filePath"
        private const val KEY_EXTENSION = "extension"
        private const val KEY_FILE_SIZE_BYTES = "fileSizeBytes"
        private const val KEY_IMPORTED_AT = "importedAt"

        val SUPPORTED_EXTENSIONS = setOf(
            "epub",
            "pdf",
            "fb2",
            "mobi",
            "azw",
            "azw3",
            "djvu",
            "djv",
            "cbz",
            "txt",
            "rtf",
            "htm",
            "html"
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

    private val rootDirectory: File
        get() = File(context.filesDir, "reader-library")

    private val booksDirectory: File
        get() = File(rootDirectory, "books")

    private val indexFile: File
        get() = File(rootDirectory, "index.json")
}
