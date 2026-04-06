package com.genesys.feature.notebook.io

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.OpenableColumns
import android.util.Log
import androidx.core.content.FileProvider
import com.genesys.feature.notebook.data.ensureBackgroundsFolder
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.Normalizer
import java.util.Locale
import kotlin.math.max
import kotlin.math.min

private const val TAG = "NotebookIO"

/**
 * Convert a content URI to a Bitmap.
 * Ported from Notable's FileUtils.saveImageFromContentUri with size-limiting.
 */
fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
    return try {
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        ImageDecoder.decodeBitmap(source) { decoder, _, _ ->
            decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
        }
    } catch (e: Exception) {
        Log.e(TAG, "Failed to decode bitmap from URI: $uri", e)
        // Fallback to stream decode
        try {
            context.contentResolver.openInputStream(uri)?.use { stream ->
                BitmapFactory.decodeStream(stream)
            }
        } catch (e2: Exception) {
            Log.e(TAG, "Fallback decode also failed: $uri", e2)
            null
        }
    }
}

/**
 * Load a background bitmap from a file path and page number.
 * For PDFs, renders the specified page using Android's PdfRenderer.
 * For images, loads directly.
 */
fun loadBackgroundBitmap(path: String, pageNumber: Int, scale: Float): Bitmap? {
    if (path.isBlank()) return null
    return try {
        val file = File(path)
        if (!file.exists()) return null

        if (path.endsWith(".pdf", ignoreCase = true)) {
            renderPdfPage(file, pageNumber, scale)
        } else {
            val options = BitmapFactory.Options().apply {
                if (scale < 1f) inSampleSize = (1 / scale).toInt().coerceAtLeast(1)
            }
            BitmapFactory.decodeFile(path, options)
        }
    } catch (e: Exception) {
        Log.e(TAG, "Failed to load background bitmap from: $path", e)
        null
    }
}

/**
 * Render a single PDF page to a Bitmap.
 * Ported from Notable's renderPdf.kt.
 */
private fun renderPdfPage(pdfFile: File, pageIndex: Int, scale: Float): Bitmap? {
    return try {
        val fd = ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY)
        fd.use {
            PdfRenderer(fd).use { renderer ->
                val clampedIndex = pageIndex.coerceIn(0, renderer.pageCount - 1)
                renderer.openPage(clampedIndex).use { page ->
                    val width = (page.width * scale).toInt().coerceAtLeast(1)
                    val height = (page.height * scale).toInt().coerceAtLeast(1)
                    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                    bitmap
                }
            }
        }
    } catch (e: Exception) {
        Log.e(TAG, "Failed to render PDF page $pageIndex from ${pdfFile.path}", e)
        null
    }
}

/**
 * Get the number of pages in a PDF file.
 * Ported from Notable's FileUtils.getPdfPageCount.
 */
fun getPdfPageCount(path: String): Int {
    if (path.isEmpty()) return 0
    val file = File(path)
    if (!file.exists()) {
        Log.w(TAG, "getPdfPageCount: File does not exist: $path")
        return 0
    }
    return try {
        val fd = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        PdfRenderer(fd).use { renderer -> renderer.pageCount }
    } catch (e: Exception) {
        Log.e(TAG, "getPdfPageCount failed for: $path", e)
        0
    }
}

/**
 * Share a bitmap via Android share intent.
 * Ported from Notable's share.kt.
 */
fun shareBitmap(context: Context, bitmap: Bitmap, title: String = "Share") {
    try {
        val cacheDir = File(context.cacheDir, "shared_images").also { it.mkdirs() }
        val file = File(cacheDir, "share_${System.currentTimeMillis()}.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        val authority = "${context.packageName}.fileprovider"
        val uri = FileProvider.getUriForFile(context, authority, file)
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(shareIntent, title)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    } catch (e: Exception) {
        Log.e(TAG, "shareBitmap failed", e)
    }
}

/**
 * Copy a bitmap to the system clipboard.
 * Ported from Notable's IO layer.
 */
fun copyBitmapToClipboard(context: Context, bitmap: Bitmap) {
    try {
        val cacheDir = File(context.cacheDir, "clipboard_images").also { it.mkdirs() }
        val file = File(cacheDir, "clipboard_${System.currentTimeMillis()}.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        val authority = "${context.packageName}.fileprovider"
        val uri = FileProvider.getUriForFile(context, authority, file)
        val clipData = ClipData.newUri(context.contentResolver, "Notebook Image", uri)
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(clipData)
    } catch (e: Exception) {
        Log.e(TAG, "copyBitmapToClipboard failed", e)
    }
}

/**
 * Check if a URI points to an image MIME type.
 * Ported from Notable's FileUtils.isImageUri.
 */
fun isImageUri(context: Context, uri: Uri): Boolean {
    val mimeType = context.contentResolver.getType(uri)
    return mimeType?.startsWith("image/") == true
}

/**
 * Save an image from a content URI to an output directory, with size-limiting.
 * Ported from Notable's FileUtils.saveImageFromContentUri.
 */
fun saveImageFromContentUri(context: Context, fileUri: Uri, outputDir: File): File {
    val fileName = getFileNameFromUri(context, fileUri)
    val destFile = File(outputDir, fileName)

    val minDimension = 2048
    val maxW = max(2048, minDimension)
    val maxH = max(2048, minDimension)

    return try {
        val source = ImageDecoder.createSource(context.contentResolver, fileUri)
        val resized = ImageDecoder.decodeBitmap(source) { decoder, info, _ ->
            val origW = info.size.width
            val origH = info.size.height
            val scale = min(1.0f, min(maxW.toFloat() / origW, maxH.toFloat() / origH))
            val targetW = max(1, (origW * scale).toInt())
            val targetH = max(1, (origH * scale).toInt())
            decoder.setTargetSize(targetW, targetH)
            decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
        }
        val mime = context.contentResolver.getType(fileUri) ?: ""
        val format = when {
            mime.equals("image/png", true) || destFile.extension.equals("png", true) ->
                Bitmap.CompressFormat.PNG
            else -> Bitmap.CompressFormat.JPEG
        }
        destFile.outputStream().use { out ->
            resized.compress(format, if (format == Bitmap.CompressFormat.PNG) 100 else 90, out)
        }
        if (!resized.isRecycled) resized.recycle()
        destFile
    } catch (e: Exception) {
        Log.e(TAG, "saveImageFromContentUri resize failed, falling back to raw copy", e)
        createFileFromContentUri(context, fileUri, outputDir)
    }
}

/**
 * Copy a file from a content URI to an output directory (raw bytes).
 * Ported from Notable's FileUtils.createFileFromContentUri.
 */
fun createFileFromContentUri(context: Context, fileUri: Uri, outputDir: File): File {
    val fileName = getFileNameFromUri(context, fileUri)
    val outputFile = File(outputDir, fileName)
    val iStream: InputStream = context.contentResolver.openInputStream(fileUri)!!
    iStream.use { input ->
        FileOutputStream(outputFile).use { output ->
            val buffer = ByteArray(4 * 1024)
            while (true) {
                val byteCount = input.read(buffer)
                if (byteCount < 0) break
                output.write(buffer, 0, byteCount)
            }
            output.flush()
        }
    }
    return outputFile
}

/**
 * Get the display name of a file from its content URI.
 * Ported from Notable's FileUtils.getFileNameFromUri.
 */
fun getFileNameFromUri(context: Context, fileUri: Uri): String {
    var fileName: String? = null
    context.contentResolver.query(fileUri, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (nameIndex >= 0 && cursor.moveToFirst()) {
            fileName = cursor.getString(nameIndex)
        }
    }
    if (fileName.isNullOrBlank()) {
        val ext = when (context.contentResolver.getType(fileUri)?.lowercase(Locale.US)) {
            "image/png" -> ".png"
            "image/webp" -> ".webp"
            "image/jpg", "image/jpeg" -> ".jpg"
            else -> ""
        }
        fileName = "file_${System.currentTimeMillis()}${ext}"
    }
    return sanitizeFileName(fileName!!)
}

/**
 * Sanitize a filename: remove illegal characters, normalize accents, enforce max length.
 * Ported from Notable's FileUtils.sanitizeFileName.
 */
fun sanitizeFileName(raw: String, maxLen: Int = 80): String {
    var name = Normalizer.normalize(raw, Normalizer.Form.NFD)
        .replace(Regex("\\p{Mn}+"), "")
    name = name.replace(Regex("""[\\/:*?"<>|]"""), " ")
    name = name.replace(Regex("[ ]+"), " ").trim()
    name = name.replace(Regex("[_]+"), "_").trim()
    name = name.trim('.')
    if (name.length > maxLen) {
        val dot = name.lastIndexOf('.')
        name = if (dot <= 0 || dot >= name.length - 1) name.take(maxLen).trimEnd()
        else {
            val ext = name.substring(dot)
            name.take(dot).take(maxLen - ext.length).trimEnd().trimEnd('.') + ext
        }
    }
    if (name.isBlank()) name = "notable-export"
    return name
}

/**
 * Export format options.
 */
enum class ExportFormat {
    PNG, SVG, PDF, JPEG, XOPP
}

/**
 * Export target options.
 */
enum class ExportTarget {
    FILE, SHARE
}

/**
 * Export engine - handles rendering and exporting notebook pages.
 * TODO: Port full PDF/SVG/XOPP export rendering from Notable.
 */
object ExportEngine {
    fun export(context: Context, format: ExportFormat, target: ExportTarget) {
        Log.w(TAG, "ExportEngine.export: stub - full export rendering not yet ported")
    }
}

/**
 * Export to linked file.
 * TODO: Port full implementation from Notable.
 */
fun exportToLinkedFile(context: Context, format: ExportFormat) {
    Log.w(TAG, "exportToLinkedFile: stub - needs export implementation")
}

/**
 * Get the linked files directory.
 * Ported from Notable's FileUtils.getLinkedFilesDir.
 */
fun getLinkedFilesDir(): File {
    val documentsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
    val dir = File(documentsDir, "/notable/Linked")
    if (!dir.exists()) dir.mkdirs()
    return dir
}
