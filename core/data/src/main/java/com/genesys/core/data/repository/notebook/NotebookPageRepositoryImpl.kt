package com.genesys.core.data.repository.notebook

import com.genesys.core.database.dao.NotebookPageDao
import com.genesys.core.database.entity.mapper.toDomain
import com.genesys.core.database.entity.mapper.toDomainImages
import com.genesys.core.database.entity.mapper.toDomainPages
import com.genesys.core.database.entity.mapper.toDomainStrokes
import com.genesys.core.database.entity.mapper.toEntity
import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.domain.repository.notebook.PageWithImages
import com.genesys.core.domain.repository.notebook.PageWithStrokes
import com.genesys.core.model.notebook.NotebookPage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Room-backed implementation of [NotebookPageRepository].
 */
class NotebookPageRepositoryImpl @Inject constructor(
    private val pageDao: NotebookPageDao
) : NotebookPageRepository {

    override suspend fun create(page: NotebookPage): Long =
        pageDao.create(page.toEntity())

    override suspend fun update(page: NotebookPage) =
        pageDao.update(page.toEntity())

    override suspend fun updateScroll(pageId: String, scroll: Int) =
        pageDao.updateScroll(pageId, scroll)

    override suspend fun getById(pageId: String): NotebookPage? =
        pageDao.getById(pageId)?.toDomain()

    override suspend fun getByIds(ids: List<String>): List<NotebookPage> =
        pageDao.getByIds(ids).toDomainPages()

    override suspend fun getWithStrokesById(pageId: String): PageWithStrokes {
        val entity = pageDao.getPageWithStrokesById(pageId)
        return PageWithStrokes(
            page = entity.page.toDomain(),
            strokes = entity.strokes.toDomainStrokes()
        )
    }

    override suspend fun getWithImagesById(pageId: String): PageWithImages {
        val entity = pageDao.getPageWithImagesById(pageId)
        return PageWithImages(
            page = entity.page.toDomain(),
            images = entity.images.toDomainImages()
        )
    }

    override fun observeStandalonePages(folderId: String?): Flow<List<NotebookPage>> =
        pageDao.observeStandalonePages(folderId).map { it.toDomainPages() }

    override suspend fun delete(pageId: String) =
        pageDao.delete(pageId)
}
