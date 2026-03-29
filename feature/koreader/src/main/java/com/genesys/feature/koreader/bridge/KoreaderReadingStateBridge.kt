package com.genesys.feature.koreader.bridge

import android.content.Context
import com.genesys.feature.koreader.runtime.KoreaderDirectories
import timber.log.Timber
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Reads KOReader-managed reading state for use by Kotlin-side surfaces
 * (recents, resume, library views).
 *
 * KOReader stores per-document state in .sdr/metadata.lua files and
 * global history in settings.reader.lua. This bridge parses enough of
 * that data to support app-side resume and recent-document features
 * without scraping KOReader's UI state at runtime.
 */
@Singleton
class KoreaderReadingStateBridge @Inject constructor() {

    /**
     * Minimal reading state for a single document.
     */
    data class DocumentState(
        val filePath: String,
        val lastOpenedTimestamp: Long,
        val currentPage: Int?,
        val totalPages: Int?,
        val percentComplete: Float?
    )

    /**
     * Get the most recently read documents from KOReader's history.
     *
     * Reads KOReader's history file (settings.reader.lua) to extract
     * recently opened documents.
     */
    fun getRecentDocuments(context: Context, limit: Int = 10): List<DocumentState> {
        val dirs = KoreaderDirectories(context)
        val historyDir = dirs.historyDir

        if (!historyDir.exists()) {
            Timber.d("No KOReader history directory found")
            return emptyList()
        }

        // KOReader stores history as .lua files in the history directory
        // Each file name encodes the document path
        return try {
            historyDir.listFiles { file -> file.extension == "lua" }
                ?.sortedByDescending { it.lastModified() }
                ?.take(limit)
                ?.mapNotNull { parseHistoryEntry(it) }
                ?: emptyList()
        } catch (e: Exception) {
            Timber.e(e, "Failed to read KOReader history")
            emptyList()
        }
    }

    /**
     * Get the last-read document path, if any.
     */
    fun getLastReadDocument(context: Context): DocumentState? {
        return getRecentDocuments(context, limit = 1).firstOrNull()
    }

    /**
     * Parse a KOReader history entry file.
     *
     * History files are Lua tables; we extract minimal fields without
     * a full Lua parser by scanning for known patterns.
     */
    private fun parseHistoryEntry(file: File): DocumentState? {
        return try {
            val content = file.readText()

            // Extract file path from the history file name
            // KOReader encodes paths in filenames using # as separator
            val docPath = decodeHistoryFilename(file.nameWithoutExtension)

            // Try to extract page info from the Lua table content
            val page = extractLuaInt(content, "page")
            val totalPages = extractLuaInt(content, "doc_pages")
            val percent = if (page != null && totalPages != null && totalPages > 0) {
                page.toFloat() / totalPages.toFloat()
            } else {
                null
            }

            DocumentState(
                filePath = docPath,
                lastOpenedTimestamp = file.lastModified(),
                currentPage = page,
                totalPages = totalPages,
                percentComplete = percent
            )
        } catch (e: Exception) {
            Timber.w(e, "Could not parse history entry: ${file.name}")
            null
        }
    }

    /**
     * Decode a KOReader history filename back to the original document path.
     * KOReader replaces / with # in history filenames.
     */
    private fun decodeHistoryFilename(name: String): String {
        return name.replace("#", "/")
    }

    /**
     * Simple pattern extraction for integer values from Lua table text.
     * Looks for patterns like: ["key"] = 42
     */
    private fun extractLuaInt(content: String, key: String): Int? {
        val pattern = Regex("""\["$key"\]\s*=\s*(\d+)""")
        return pattern.find(content)?.groupValues?.get(1)?.toIntOrNull()
    }
}
