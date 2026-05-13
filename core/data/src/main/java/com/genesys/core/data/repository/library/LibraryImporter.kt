package com.genesys.core.data.repository.library

import com.genesys.core.domain.repository.library.LibraryFileSupport
import com.genesys.core.model.library.LibraryImportResult

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LibraryImporter @Inject constructor(
    @ApplicationContext private val context: Context,
    private val storage: LibraryStorage
) {

    fun importDocuments(uris: List<Uri>): LibraryImportResult {
        storage.ensureStorage()

        val records = storage.readIndex().toMutableList()
        var importedCount = 0
        val skippedUnsupported = mutableListOf<String>()
        val failed = mutableListOf<String>()

        uris.forEachIndexed { index, uri ->
            val displayName = resolveDisplayName(uri)
                ?.takeIf { it.isNotBlank() }
                ?: "book-${System.currentTimeMillis()}-$index"

            val extension = displayName.substringAfterLast('.', "").lowercase()
            if (extension !in LibraryFileSupport.supportedExtensions) {
                skippedUnsupported += displayName
                return@forEachIndexed
            }

            val targetFile = storage.createUniqueTargetFile(displayName)
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

                records += storage.createStoredBook(targetFile, displayName, extension)
                importedCount += 1
            } catch (error: Exception) {
                targetFile.delete()
                failed += displayName
                Timber.e(error, "Failed to import reader document: %s", displayName)
            }
        }

        storage.writeIndex(records)
        return LibraryImportResult(
            importedCount = importedCount,
            skippedUnsupported = skippedUnsupported,
            failed = failed
        )
    }

    fun importFile(filePath: String): LibraryImportResult {
        storage.ensureStorage()

        val sourceFile = File(filePath)
        val displayName = sourceFile.name.ifBlank {
            "book-${System.currentTimeMillis()}"
        }
        val extension = displayName.substringAfterLast('.', "").lowercase()

        if (!sourceFile.exists() || !sourceFile.isFile) {
            return LibraryImportResult(0, emptyList(), listOf(displayName))
        }

        if (extension !in LibraryFileSupport.supportedExtensions) {
            return LibraryImportResult(0, listOf(displayName), emptyList())
        }

        val records = storage.readIndex().toMutableList()
        val targetFile = storage.createUniqueTargetFile(displayName)

        return try {
            sourceFile.inputStream().buffered().use { input ->
                targetFile.outputStream().buffered().use { output ->
                    input.copyTo(output)
                }
            }

            records += storage.createStoredBook(targetFile, displayName, extension)
            storage.writeIndex(records)

            LibraryImportResult(1, emptyList(), emptyList())
        } catch (error: Exception) {
            targetFile.delete()
            Timber.e(error, "Failed to import reader file path: %s", filePath)
            LibraryImportResult(0, emptyList(), listOf(displayName))
        }
    }

    private fun resolveDisplayName(uri: Uri): String? {
        return try {
            context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                if (!cursor.moveToFirst()) return@use null
                val columnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (columnIndex >= 0) cursor.getString(columnIndex) else null
            }
        } catch (error: Exception) {
            Timber.w(error, "Unable to resolve display name for reader document: %s", uri)
            null
        }
    }
}
