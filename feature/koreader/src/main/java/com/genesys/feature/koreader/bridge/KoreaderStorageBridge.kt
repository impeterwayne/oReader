package com.genesys.feature.koreader.bridge

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import com.genesys.feature.koreader.runtime.KoreaderDirectories
import timber.log.Timber
import java.io.File
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Bridges Android content URIs and intents into KOReader-compatible file paths.
 *
 * KOReader operates on real filesystem paths. This bridge handles:
 * - Converting content:// URIs to staged file copies
 * - Extracting file paths from ACTION_VIEW and share intents
 * - Cleaning up staged files after use
 */
@Singleton
class KoreaderStorageBridge @Inject constructor() {

    companion object {
        private val INVALID_FILE_NAME_CHARS = Regex("""[\\/:*?"<>|]""")
    }

    /**
     * Result of resolving a document for KOReader.
     */
    sealed class ResolvedDocument {
        /** Document is available at the given filesystem path. */
        data class Available(val filePath: String, val isStaged: Boolean) : ResolvedDocument()

        /** Document could not be resolved. */
        data class Unavailable(val reason: String) : ResolvedDocument()
    }

    /**
     * Resolve an Android Intent into a KOReader-compatible file path.
     *
     * Handles:
     * - file:// URIs → direct path
     * - content:// URIs → staged copy in app-private storage
     * - Extra file path strings → direct path
     */
    fun resolveIntent(context: Context, intent: Intent): ResolvedDocument {
        // Try data URI first
        val uri = intent.data
        if (uri != null) {
            return resolveUri(context, uri)
        }

        // Try EXTRA_STREAM (share intents)
        val streamUri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
        if (streamUri != null) {
            return resolveUri(context, streamUri)
        }

        return ResolvedDocument.Unavailable("No document URI found in intent")
    }

    /**
     * Resolve a URI into a KOReader-compatible file path.
     */
    fun resolveUri(context: Context, uri: Uri): ResolvedDocument {
        return when (uri.scheme) {
            ContentResolver.SCHEME_FILE -> {
                val path = uri.path
                if (path != null && File(path).exists()) {
                    ResolvedDocument.Available(path, isStaged = false)
                } else {
                    ResolvedDocument.Unavailable("File not found: $path")
                }
            }

            ContentResolver.SCHEME_CONTENT -> {
                stageContentUri(context, uri)
            }

            else -> {
                ResolvedDocument.Unavailable("Unsupported URI scheme: ${uri.scheme}")
            }
        }
    }

    private fun stageContentUri(context: Context, uri: Uri): ResolvedDocument {
        val dirs = KoreaderDirectories(context)
        dirs.ensureDirectories()

        val stagedFile = createStagedFile(context, uri)
        if (stagedFile.exists() && stagedFile.length() > 0L) {
            return ResolvedDocument.Available(stagedFile.absolutePath, isStaged = true)
        }

        return try {
            context.contentResolver.openInputStream(uri)?.use { input ->
                stagedFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            } ?: return ResolvedDocument.Unavailable("Cannot open content URI: $uri")

            Timber.i("Staged document: $uri -> ${stagedFile.absolutePath}")
            ResolvedDocument.Available(stagedFile.absolutePath, isStaged = true)
        } catch (e: Exception) {
            stagedFile.delete()
            Timber.e(e, "Failed to stage content URI: $uri")
            ResolvedDocument.Unavailable("Failed to stage document: ${e.message}")
        }
    }

    fun previewStagedFilePath(context: Context, uri: Uri): String {
        return createStagedFile(context, uri).absolutePath
    }

    private fun createStagedFile(context: Context, uri: Uri): File {
        val dirs = KoreaderDirectories(context)
        dirs.ensureDirectories()

        val displayName = sanitizeFileName(
            getDisplayName(context, uri)?.takeIf { it.isNotBlank() }
                ?: "document_${uri.hashCode()}"
        )
        val nameWithoutExtension = displayName.substringBeforeLast('.', displayName)
        val extension = displayName.substringAfterLast('.', "")
        val uriSuffix = uri.toString().hashCode().toUInt().toString(16)
        val uniqueName = if (extension.isNotBlank()) {
            "$nameWithoutExtension-$uriSuffix.$extension"
        } else {
            "$nameWithoutExtension-$uriSuffix"
        }

        return File(dirs.stagingDir, uniqueName)
    }

    private fun sanitizeFileName(fileName: String): String {
        val sanitized = fileName.replace(INVALID_FILE_NAME_CHARS, "_").trim()
        return if (sanitized.isBlank()) {
            "document_${UUID.randomUUID()}"
        } else {
            sanitized
        }
    }

    /**
     * Get the display name of a content:// URI document.
     */
    private fun getDisplayName(context: Context, uri: Uri): String? {
        return try {
            context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (nameIndex >= 0) cursor.getString(nameIndex) else null
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            Timber.w(e, "Could not query display name for: $uri")
            null
        }
    }

    /**
     * Clean up old staged files.
     */
    fun cleanupStagedFiles(context: Context, maxAgeMs: Long = 24 * 60 * 60 * 1000L) {
        val dirs = KoreaderDirectories(context)
        dirs.cleanStagedFiles(maxAgeMs)
    }
}
