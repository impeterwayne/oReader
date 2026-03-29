package com.genesys.feature.koreader.runtime

import android.content.Context
import java.io.File

/**
 * Defines and manages KOReader's app-private directory layout.
 *
 * KOReader expects writable directories for settings, cache, history,
 * document sidecars, dictionaries, fonts, and plugins. This class
 * centralizes that layout within Android's app-private storage.
 */
class KoreaderDirectories(context: Context) {

    /** Root data directory for KOReader runtime files. */
    val dataDir: File = File(context.filesDir, "koreader")

    /** KOReader settings (settings.reader.lua, etc.) */
    val settingsDir: File = File(dataDir, "settings")

    /** Cache for rendered tiles, temporary downloads, etc. */
    val cacheDir: File = File(context.cacheDir, "koreader")

    /** Reading history database */
    val historyDir: File = File(dataDir, "history")

    /** Document sidecars (.sdr directories for bookmarks, highlights, progress) */
    val sidecarsDir: File = File(dataDir, "sidecars")

    /** Installed dictionaries */
    val dictionariesDir: File = File(dataDir, "dictionaries")

    /** Custom fonts */
    val fontsDir: File = File(dataDir, "fonts")

    /** KOReader plugins */
    val pluginsDir: File = File(dataDir, "plugins")

    /** Staged files from content:// URIs */
    val stagingDir: File = File(cacheDir, "staged")

    /**
     * Ensure all required directories exist.
     */
    fun ensureDirectories() {
        listOf(
            dataDir,
            settingsDir,
            cacheDir,
            historyDir,
            sidecarsDir,
            dictionariesDir,
            fontsDir,
            pluginsDir,
            stagingDir
        ).forEach { it.mkdirs() }
    }

    /**
     * Clean up temporary staged files older than the given threshold.
     */
    fun cleanStagedFiles(maxAgeMs: Long = 24 * 60 * 60 * 1000L) {
        val cutoff = System.currentTimeMillis() - maxAgeMs
        stagingDir.listFiles()?.forEach { file ->
            if (file.lastModified() < cutoff) {
                file.delete()
            }
        }
    }
}
