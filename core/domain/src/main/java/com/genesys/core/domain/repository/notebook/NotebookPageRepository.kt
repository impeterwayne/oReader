package com.genesys.core.domain.repository.notebook

import com.genesys.core.model.notebook.NotebookImage
import com.genesys.core.model.notebook.NotebookPage
import com.genesys.core.model.notebook.NotebookStroke
import kotlinx.coroutines.flow.Flow

/**
 * Data holder for a page together with its child strokes.
 */
data class PageWithStrokes(
    val page: NotebookPage,
    val strokes: List<NotebookStroke>
)

/**
 * Data holder for a page together with its child images.
 */
data class PageWithImages(
    val page: NotebookPage,
    val images: List<NotebookImage>
)

/**
 * Repository contract for notebook page operations.
 *
 * Ported from Notable's PageRepository with LiveData→Flow migration.
 */
interface NotebookPageRepository {

    suspend fun create(page: NotebookPage): Long

    suspend fun update(page: NotebookPage)

    suspend fun updateScroll(pageId: String, scroll: Int)

    suspend fun getById(pageId: String): NotebookPage?

    suspend fun getByIds(ids: List<String>): List<NotebookPage>

    suspend fun getWithStrokesById(pageId: String): PageWithStrokes

    suspend fun getWithImagesById(pageId: String): PageWithImages

    /** Observe standalone (non-notebook) pages in a folder. */
    fun observeStandalonePages(folderId: String? = null): Flow<List<NotebookPage>>

    suspend fun delete(pageId: String)
}
