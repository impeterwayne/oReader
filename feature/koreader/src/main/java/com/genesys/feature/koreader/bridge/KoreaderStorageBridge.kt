package com.genesys.feature.koreader.bridge

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import com.genesys.feature.koreader.runtime.KoreaderDirectories
import timber.log.Timber
import java.io.File
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

    /**
     * Stage a content:// URI by copying its contents to app-private storage.
     *
     * Uses a deterministic filename based on the URI to avoid redundant copies.
     */
    private fun stageContentUri(context: Context, uri: Uri): ResolvedDocument {
        val dirs = KoreaderDirectories(context)
        dirs.ensureDirectories()

        val displayName = getDisplayName(context, uri)
            ?: "document_${uri.hashCode()}"

        val stagedFile = File(dirs.stagingDir, displayName)

        return try {
            // Copy content to staging directory
            context.contentResolver.openInputStream(uri)?.use { input ->
                stagedFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            } ?: return ResolvedDocument.Unavailable("Cannot open content URI: $uri")

            Timber.i("Staged document: $uri -> ${stagedFile.absolutePath}")
            ResolvedDocument.Available(stagedFile.absolutePath, isStaged = true)
        } catch (e: Exception) {
            Timber.e(e, "Failed to stage content URI: $uri")
            ResolvedDocument.Unavailable("Failed to stage document: ${e.message}")
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
