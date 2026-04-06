package com.genesys.feature.notebook.data

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.feature.notebook.editor.settings.NotebookEditorSettings
import com.genesys.feature.notebook.editor.settings.NotebookSettingsProvider
import com.genesys.feature.notebook.io.createFileFromContentUri
import com.genesys.feature.notebook.io.isImageUri
import com.genesys.feature.notebook.io.saveImageFromContentUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

private const val TAG = "DbUtils"

fun getDbDir(): File {
    val documentsDir =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
    val dbDir = File(documentsDir, "notabledb")
    if (!dbDir.exists()) {
        dbDir.mkdirs()
    }
    return dbDir
}

fun ensureImagesFolder(): File {
    val dbDir = getDbDir()
    val imagesDir = File(dbDir, "images")
    if (!imagesDir.exists()) {
        imagesDir.mkdirs()
    }
    return imagesDir
}

fun ensureBackgroundsFolder(): File {
    val dbDir = getDbDir()
    val backgroundsDir = File(dbDir, "backgrounds")
    if (!backgroundsDir.exists()) {
        backgroundsDir.mkdirs()
    }
    return backgroundsDir
}

fun ensurePreviewsFullFolder(context: Context): File {
    val dir = File(context.filesDir, "pages/previews/full")
    if (!dir.exists()) {
        dir.mkdirs()
    }
    return dir
}

/**
 * Copy an image or PDF URI to the database's backgrounds folder.
 * Ported from Notable's dbUtils.copyBackgroundToDatabase.
 * Images are resized to avoid overly large bitmaps; PDFs are copied raw.
 */
fun copyBackgroundToDatabase(context: Context, fileUri: Uri, subfolder: String): File {
    var outputDir = ensureBackgroundsFolder()
    outputDir = File(outputDir, subfolder)
    if (!outputDir.exists()) outputDir.mkdirs()
    return if (isImageUri(context, fileUri))
        saveImageFromContentUri(context, fileUri, outputDir)
    else
        createFileFromContentUri(context, fileUri, outputDir)
}

/**
 * Copy an image to the database's images folder.
 */
fun copyImageToDatabase(context: Context, fileUri: Uri, subfolder: String? = null): File {
    var outputDir = ensureImagesFolder()
    if (subfolder != null) {
        outputDir = File(outputDir, subfolder)
        if (!outputDir.exists())
            outputDir.mkdirs()
    }
    // TODO: port saveImageFromContentUri from Notable IO
    Log.w(TAG, "copyImageToDatabase: stub - needs IO layer port")
    return outputDir
}

/**
 * Delete a page and clean up related resources.
 */
suspend fun deletePage(
    pageRepository: NotebookPageRepository,
    notebookRepository: NotebookRepository,
    pageId: String,
    filesDir: File
) = withContext(Dispatchers.IO) {
    val page = pageRepository.getById(pageId) ?: return@withContext

    // remove from notebook
    if (page.notebookId != null) {
        notebookRepository.removePage(page.notebookId!!, pageId)
    }

    // remove from quick nav
    val settings = NotebookSettingsProvider.current
    if (settings.quickNavPages.contains(pageId)) {
        NotebookSettingsProvider.update(
            settings.copy(quickNavPages = settings.quickNavPages - pageId)
        )
    }

    pageRepository.delete(pageId)
    coroutineScope {
        launch {
            val imgFileThumb = File(filesDir, "pages/previews/thumbs/$pageId")
            if (imgFileThumb.exists()) imgFileThumb.delete()
            val imgFileFull = File(filesDir, "pages/previews/full/$pageId")
            if (imgFileFull.exists()) imgFileFull.delete()
        }
    }
}
