package com.genesys.core.data.repository.notebook

import com.genesys.core.database.dao.NotebookDao
import com.genesys.core.database.dao.NotebookPageDao
import com.genesys.core.database.entity.mapper.toDomain
import com.genesys.core.database.entity.mapper.toDomainNotebooks
import com.genesys.core.database.entity.mapper.toEntity
import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.core.model.notebook.Notebook
import com.genesys.core.model.notebook.NotebookPage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

/**
 * Room-backed implementation of [NotebookRepository].
 *
 * Preserves Notable's `BookRepository` semantics:
 * - `create()` inserts a notebook **and** an initial page atomically.
 * - `removePage()` clears `openPageId` when the removed page was the open one.
 */
class NotebookRepositoryImpl @Inject constructor(
    private val notebookDao: NotebookDao,
    private val pageDao: NotebookPageDao
) : NotebookRepository {

    override fun observeInFolder(folderId: String?): Flow<List<Notebook>> =
        notebookDao.observeInFolder(folderId).map { it.toDomainNotebooks() }

    override fun observeById(notebookId: String): Flow<Notebook?> =
        notebookDao.observeById(notebookId).map { it?.toDomain() }

    override suspend fun getById(notebookId: String): Notebook? =
        notebookDao.getById(notebookId)?.toDomain()

    override suspend fun create(notebook: Notebook) {
        notebookDao.create(notebook.toEntity())

        val page = NotebookPage(
            notebookId = notebook.id,
            background = notebook.defaultBackground,
            backgroundType = notebook.defaultBackgroundType
        )
        pageDao.create(page.toEntity())

        notebookDao.setPageIds(notebook.id, listOf(page.id))
        notebookDao.setOpenPageId(notebook.id, page.id)
    }

    override suspend fun createEmpty(notebook: Notebook) {
        notebookDao.create(notebook.toEntity())
    }

    override suspend fun update(notebook: Notebook) {
        notebookDao.update(notebook.copy(updatedAt = Date()).toEntity())
    }

    override suspend fun delete(id: String) {
        notebookDao.delete(id)
    }

    override suspend fun setOpenPageId(notebookId: String, pageId: String) {
        notebookDao.setOpenPageId(notebookId, pageId)
    }

    override suspend fun addPage(notebookId: String, pageId: String, index: Int?) {
        val notebook = notebookDao.getById(notebookId) ?: return
        val pageIds = notebook.pageIds.toMutableList()
        if (index != null) pageIds.add(index, pageId) else pageIds.add(pageId)
        notebookDao.setPageIds(notebookId, pageIds)
    }

    override suspend fun removePage(notebookId: String, pageId: String) {
        val entity = notebookDao.getById(notebookId) ?: return
        val updated = entity.copy(
            pageIds = entity.pageIds.filterNot { it == pageId },
            openPageId = if (entity.openPageId == pageId) null else entity.openPageId
        )
        notebookDao.update(updated)
    }

    override suspend fun changePageIndex(notebookId: String, pageId: String, index: Int) {
        val entity = notebookDao.getById(notebookId) ?: return
        val pageIds = entity.pageIds.toMutableList()
        pageIds.remove(pageId)
        val corrected = index.coerceIn(0, pageIds.size)
        pageIds.add(corrected, pageId)
        notebookDao.setPageIds(notebookId, pageIds)
    }

    override suspend fun getPageIndex(notebookId: String, pageId: String): Int? {
        val entity = notebookDao.getById(notebookId) ?: return null
        val idx = entity.pageIds.indexOf(pageId)
        return if (idx != -1) idx else null
    }

    override suspend fun getPageAtIndex(notebookId: String, index: Int): String? {
        val entity = notebookDao.getById(notebookId) ?: return null
        return entity.pageIds.getOrNull(index)
    }
}
