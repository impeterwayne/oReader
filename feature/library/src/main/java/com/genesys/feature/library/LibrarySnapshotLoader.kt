package com.genesys.feature.library

import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import com.genesys.core.data.repository.library.LibraryFolderManager
import com.genesys.core.data.repository.library.LibraryStorage
import com.genesys.core.domain.repository.library.LibraryFileSupport
import com.genesys.core.domain.repository.library.LibrarySnapshotLoader
import com.genesys.core.model.library.Book
import com.genesys.core.model.library.BookSource
import com.genesys.core.model.library.LibraryFolder
import com.genesys.core.model.library.LibrarySnapshot
import com.genesys.feature.koreader.bridge.KoreaderReadingStateBridge
import com.genesys.feature.koreader.bridge.KoreaderStorageBridge
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
internal class LibrarySnapshotLoaderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val readingStateBridge: KoreaderReadingStateBridge,
    private val storageBridge: KoreaderStorageBridge,
    private val storage: LibraryStorage,
    private val folderManager: LibraryFolderManager
) : LibrarySnapshotLoader {

    override fun getLibrarySnapshot(): LibrarySnapshot {
        val readingStateByPath = readingStateBridge
            .getRecentDocuments(context, limit = 500)
            .associateBy { it.filePath }

        var indexChanged = false
        val managedRecords = storage.readIndex().filter { record ->
            val exists = File(record.filePath).exists()
            if (!exists) {
                indexChanged = true
            }
            exists
        }

        if (indexChanged) {
            storage.writeIndex(managedRecords)
        }

        val folderStates = folderManager.getFolderStates()
        val managedBooks = managedRecords.map { record ->
            val readingState = readingStateByPath[record.filePath]
            Book(
                id = record.id,
                title = record.title,
                fileName = record.fileName,
                filePath = record.filePath,
                extension = record.extension,
                fileSizeBytes = record.fileSizeBytes,
                addedAt = record.importedAt,
                locationLabel = "Managed library",
                source = BookSource.ManagedCopy,
                lastOpenedAt = readingState?.lastOpenedTimestamp,
                percentComplete = readingState?.percentComplete
            )
        }
        val safBooks = scanSelectedFolders(folderStates, readingStateByPath)

        return LibrarySnapshot(
            books = (managedBooks + safBooks)
                .distinctBy { it.contentUri ?: it.filePath }
                .sortedWith(
                    compareByDescending<Book> { it.lastOpenedAt ?: 0L }
                        .thenByDescending { it.addedAt }
                        .thenBy { it.title.lowercase() }
                ),
            selectedFolders = folderStates,
            invalidFolders = folderStates.filterNot { it.isValid }
        )
    }

    private fun scanSelectedFolders(
        folderStates: List<LibraryFolder>,
        readingStateByPath: Map<String, KoreaderReadingStateBridge.DocumentState>
    ): List<Book> {
        val books = mutableListOf<Book>()
        val seenUris = mutableSetOf<String>()

        folderStates.filter { it.isValid }.forEach { folder ->
            val treeUri = Uri.parse(folder.treeUri)
            val root = DocumentFile.fromTreeUri(context, treeUri) ?: return@forEach
            collectBooksFromDocumentTree(
                root = root,
                folderName = folder.displayName,
                readingStateByPath = readingStateByPath,
                seenUris = seenUris,
                books = books
            )
        }

        Timber.i("Reader library SAF scan found %d books", books.size)
        return books
    }

    private fun collectBooksFromDocumentTree(
        root: DocumentFile,
        folderName: String,
        readingStateByPath: Map<String, KoreaderReadingStateBridge.DocumentState>,
        seenUris: MutableSet<String>,
        books: MutableList<Book>
    ) {
        val stack = ArrayDeque<Pair<DocumentFile, String>>()
        stack.add(root to folderName)

        while (stack.isNotEmpty()) {
            val (current, pathLabel) = stack.removeLast()
            val children = try {
                current.listFiles()
            } catch (error: Exception) {
                Timber.w(error, "Reader library scan could not list %s", current.uri)
                emptyArray()
            }

            children.forEach { child ->
                try {
                    if (child.isDirectory) {
                        val childName = child.name?.takeIf { it.isNotBlank() } ?: "Folder"
                        stack.add(child to "$pathLabel/$childName")
                        return@forEach
                    }

                    if (!child.isFile) return@forEach

                    val displayName = child.name?.takeIf { it.isNotBlank() } ?: return@forEach
                    val extension = displayName.substringAfterLast('.', "").lowercase()
                    if (extension !in LibraryFileSupport.supportedExtensions) return@forEach

                    val contentUri = child.uri.toString()
                    if (!seenUris.add(contentUri)) return@forEach

                    val stagedPath = storageBridge.previewStagedFilePath(context, child.uri)
                    val readingState = readingStateByPath[stagedPath]
                    books += Book(
                        id = contentUri,
                        title = displayName.substringBeforeLast('.', displayName),
                        fileName = displayName,
                        filePath = stagedPath,
                        extension = extension,
                        fileSizeBytes = child.length(),
                        addedAt = child.lastModified(),
                        locationLabel = pathLabel,
                        source = BookSource.SafFolder,
                        lastOpenedAt = readingState?.lastOpenedTimestamp,
                        percentComplete = readingState?.percentComplete,
                        contentUri = contentUri
                    )
                } catch (error: Exception) {
                    Timber.w(error, "Reader library scan skipped inaccessible child under %s", pathLabel)
                }
            }
        }
    }
}
