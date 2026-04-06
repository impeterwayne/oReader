package com.genesys.core.domain.repository.notebook

import com.genesys.core.model.notebook.NotebookFolder
import kotlinx.coroutines.flow.Flow

/**
 * Repository contract for notebook folder hierarchy.
 *
 * Ported from Notable's FolderRepository with LiveData→Flow migration.
 */
interface NotebookFolderRepository {

    suspend fun create(folder: NotebookFolder)

    suspend fun update(folder: NotebookFolder)

    /** Observe direct child folders of [folderId] (null = root). */
    fun observeChildren(folderId: String? = null): Flow<List<NotebookFolder>>

    /** Observe a single folder (null if not found / deleted). */
    fun observeFolder(folderId: String): Flow<NotebookFolder?>

    suspend fun get(folderId: String): NotebookFolder?

    /** Return the parentFolderId of [folderId], or null if root or not found. */
    suspend fun getParent(folderId: String?): String?

    suspend fun delete(id: String)
}
