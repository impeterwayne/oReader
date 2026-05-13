package com.genesys.codebase.reader

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.OpenableColumns
import androidx.documentfile.provider.DocumentFile
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ReaderLibraryFolderManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val storage: ReaderLibraryStorage
) {

    fun getFolderStates(): List<ReaderLibraryFolder> {
        return storage.readFolderIndex().map(::createFolderState)
    }

    fun addLibraryFolder(treeUri: Uri): Boolean {
        if (!takePersistableTreePermission(treeUri)) {
            return false
        }

        val folders = storage.readFolderIndex().toMutableList()
        val normalizedUri = treeUri.toString()
        if (folders.any { it.treeUri == normalizedUri }) {
            return true
        }

        val documentFile = DocumentFile.fromTreeUri(context, treeUri)
        val displayName = documentFile?.name?.takeIf { it.isNotBlank() }
            ?: resolveTreeDisplayName(treeUri)
            ?: "Selected folder"

        folders += StoredFolder(
            id = UUID.randomUUID().toString(),
            treeUri = normalizedUri,
            displayName = displayName,
            selectedAt = System.currentTimeMillis()
        )
        storage.writeFolderIndex(folders)
        return true
    }

    fun removeLibraryFolder(folderId: String): ReaderLibraryFolder? {
        val folders = storage.readFolderIndex().toMutableList()
        val index = folders.indexOfFirst { it.id == folderId }
        if (index < 0) {
            return null
        }

        val removed = folders.removeAt(index)
        storage.writeFolderIndex(folders)
        return createFolderState(removed)
    }

    fun removeInvalidFolders(): Boolean {
        val folders = storage.readFolderIndex()
        val validFolders = folders.filter { isFolderAccessible(Uri.parse(it.treeUri)) }
        if (validFolders.size == folders.size) {
            return false
        }
        storage.writeFolderIndex(validFolders)
        return true
    }

    private fun createFolderState(folder: StoredFolder): ReaderLibraryFolder {
        val treeUri = Uri.parse(folder.treeUri)
        if (!isTreeUri(folder.treeUri)) {
            return ReaderLibraryFolder(folder.id, folder.treeUri, folder.displayName, false, "Not a document tree")
        }
        if (!hasPersistedTreePermission(treeUri)) {
            return ReaderLibraryFolder(folder.id, folder.treeUri, folder.displayName, false, "Folder permission was revoked")
        }

        val documentFile = try {
            DocumentFile.fromTreeUri(context, treeUri)
        } catch (error: Exception) {
            Timber.w(error, "Could not resolve reader library folder: %s", folder.treeUri)
            null
        }

        val isAvailable = try {
            documentFile != null && documentFile.exists() && documentFile.isDirectory
        } catch (error: Exception) {
            Timber.w(error, "Could not access reader library folder: %s", folder.treeUri)
            false
        }
        if (!isAvailable || documentFile == null) {
            return ReaderLibraryFolder(folder.id, folder.treeUri, folder.displayName, false, "Folder is no longer available")
        }

        val displayName = try {
            documentFile.name?.takeIf { it.isNotBlank() } ?: folder.displayName
        } catch (error: Exception) {
            Timber.w(error, "Could not read reader library folder name: %s", folder.treeUri)
            folder.displayName
        }
        return ReaderLibraryFolder(folder.id, folder.treeUri, displayName, true, null)
    }

    private fun takePersistableTreePermission(treeUri: Uri): Boolean {
        return try {
            context.contentResolver.takePersistableUriPermission(treeUri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            true
        } catch (error: SecurityException) {
            Timber.w(error, "Could not persist reader library folder permission: %s", treeUri)
            false
        }
    }

    private fun hasPersistedTreePermission(treeUri: Uri): Boolean {
        return context.contentResolver.persistedUriPermissions.any { permission ->
            permission.isReadPermission && permission.uri == treeUri
        }
    }

    private fun isFolderAccessible(treeUri: Uri): Boolean {
        return hasPersistedTreePermission(treeUri) && runCatching {
            DocumentFile.fromTreeUri(context, treeUri)?.exists() == true
        }.getOrDefault(false)
    }

    private fun isTreeUri(value: String): Boolean {
        return runCatching {
            DocumentsContract.isTreeUri(Uri.parse(value))
        }.getOrDefault(false)
    }

    private fun resolveTreeDisplayName(treeUri: Uri): String? {
        return try {
            context.contentResolver.query(treeUri, null, null, null, null)?.use { cursor ->
                if (!cursor.moveToFirst()) return@use null
                val columnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (columnIndex >= 0) cursor.getString(columnIndex) else null
            }
        } catch (error: Exception) {
            Timber.w(error, "Unable to resolve folder name for reader tree URI: %s", treeUri)
            null
        }
    }
}
