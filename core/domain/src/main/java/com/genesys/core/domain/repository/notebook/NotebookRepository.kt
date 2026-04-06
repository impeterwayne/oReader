package com.genesys.core.domain.repository.notebook

import com.genesys.core.model.notebook.Notebook
import com.genesys.core.model.notebook.NotebookPage
import kotlinx.coroutines.flow.Flow

/**
 * Repository contract for notebook (book) operations.
 *
 * Ported from Notable's BookRepository with LiveData→Flow migration and
 * the create-with-first-page orchestration preserved.
 */
interface NotebookRepository {

    /** Observe all notebooks that live directly inside [folderId] (null = root). */
    fun observeInFolder(folderId: String? = null): Flow<List<Notebook>>

    /** Observe a single notebook by its id. */
    fun observeById(notebookId: String): Flow<Notebook?>

    /** One-shot fetch by id. */
    suspend fun getById(notebookId: String): Notebook?

    /**
     * Create a notebook **and** its initial page atomically.
     * Mirrors Notable's `BookRepository.create` which inserts a first page,
     * sets `pageIds`, and sets `openPageId` in one logical operation.
     */
    suspend fun create(notebook: Notebook)

    /** Create a notebook record without creating any initial pages. */
    suspend fun createEmpty(notebook: Notebook)

    /** Persist a notebook update (automatically stamps `updatedAt`). */
    suspend fun update(notebook: Notebook)

    /** Delete a notebook by id (cascades to owned pages). */
    suspend fun delete(id: String)

    /** Set the page the notebook should open to. */
    suspend fun setOpenPageId(notebookId: String, pageId: String)

    /** Append or insert a page id at [index] in the notebook's page list. */
    suspend fun addPage(notebookId: String, pageId: String, index: Int? = null)

    /** Remove a page id from the notebook (also clears openPageId if it matches). */
    suspend fun removePage(notebookId: String, pageId: String)

    /** Move [pageId] to [index] inside the notebook's page list. */
    suspend fun changePageIndex(notebookId: String, pageId: String, index: Int)

    /** Return the 0-based index of [pageId] in the notebook, or null if absent. */
    suspend fun getPageIndex(notebookId: String, pageId: String): Int?

    /** Return the page id at [index], or null if out of bounds. */
    suspend fun getPageAtIndex(notebookId: String, index: Int): String?
}
