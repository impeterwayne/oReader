package com.genesys.codebase.reader

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ReaderLibraryStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun ensureStorage() {
        rootDirectory.mkdirs()
        booksDirectory.mkdirs()
        indexFile.parentFile?.mkdirs()
        foldersFile.parentFile?.mkdirs()
    }

    fun createUniqueTargetFile(originalName: String): File {
        ensureStorage()
        val sanitizedName = sanitizeFileName(originalName)
        val title = sanitizedName.substringBeforeLast('.', sanitizedName)
        val extension = sanitizedName.substringAfterLast('.', "")

        var candidate = if (extension.isNotBlank()) File(booksDirectory, "$title.$extension") else File(booksDirectory, title)
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

    fun createStoredBook(targetFile: File, originalName: String, extension: String): StoredBook {
        return StoredBook(
            id = java.util.UUID.randomUUID().toString(),
            title = originalName.substringBeforeLast('.', originalName),
            fileName = targetFile.name,
            filePath = targetFile.absolutePath,
            extension = extension,
            fileSizeBytes = targetFile.length(),
            importedAt = System.currentTimeMillis()
        )
    }

    fun readIndex(): List<StoredBook> {
        if (!indexFile.exists()) return emptyList()
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
            }.filter { it.id.isNotBlank() && it.title.isNotBlank() && it.filePath.isNotBlank() }
        } catch (error: Exception) {
            Timber.e(error, "Failed to parse reader library index")
            emptyList()
        }
    }

    fun writeIndex(records: List<StoredBook>) {
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

    fun readFolderIndex(): List<StoredFolder> {
        if (!foldersFile.exists()) return emptyList()
        return try {
            val entries = JSONArray(foldersFile.readText())
            buildList(entries.length()) {
                repeat(entries.length()) { index ->
                    val item = entries.optJSONObject(index) ?: return@repeat
                    add(
                        StoredFolder(
                            id = item.optString(KEY_ID),
                            treeUri = item.optString(KEY_TREE_URI),
                            displayName = item.optString(KEY_DISPLAY_NAME),
                            selectedAt = item.optLong(KEY_SELECTED_AT)
                        )
                    )
                }
            }.filter { it.id.isNotBlank() && it.treeUri.isNotBlank() && it.displayName.isNotBlank() }
        } catch (error: Exception) {
            Timber.e(error, "Failed to parse reader folder index")
            emptyList()
        }
    }

    fun writeFolderIndex(records: List<StoredFolder>) {
        ensureStorage()
        val payload = JSONArray().apply {
            records.forEach { record ->
                put(
                    JSONObject()
                        .put(KEY_ID, record.id)
                        .put(KEY_TREE_URI, record.treeUri)
                        .put(KEY_DISPLAY_NAME, record.displayName)
                        .put(KEY_SELECTED_AT, record.selectedAt)
                )
            }
        }
        foldersFile.writeText(payload.toString())
    }

    private fun sanitizeFileName(fileName: String): String {
        val cleaned = fileName.replace(INVALID_FILE_NAME_CHARS, "_").trim()
        return if (cleaned.isBlank()) "book-${System.currentTimeMillis()}" else cleaned
    }

    private val rootDirectory: File
        get() = File(context.filesDir, "reader-library")
    private val booksDirectory: File
        get() = File(rootDirectory, "books")
    private val indexFile: File
        get() = File(rootDirectory, "index.json")
    private val foldersFile: File
        get() = File(rootDirectory, "folders.json")

    private companion object {
        private val INVALID_FILE_NAME_CHARS = Regex("""[\\/:*?\"<>|]""")
        private const val KEY_ID = "id"
        private const val KEY_TITLE = "title"
        private const val KEY_FILE_NAME = "fileName"
        private const val KEY_FILE_PATH = "filePath"
        private const val KEY_EXTENSION = "extension"
        private const val KEY_FILE_SIZE_BYTES = "fileSizeBytes"
        private const val KEY_IMPORTED_AT = "importedAt"
        private const val KEY_TREE_URI = "treeUri"
        private const val KEY_DISPLAY_NAME = "displayName"
        private const val KEY_SELECTED_AT = "selectedAt"
    }
}
