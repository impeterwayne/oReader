package com.genesys.core.data.repository.notebook

import com.genesys.core.database.dao.NotebookStrokeDao
import com.genesys.core.database.entity.mapper.toDomain
import com.genesys.core.database.entity.mapper.toEntity
import com.genesys.core.domain.repository.notebook.NotebookStrokeRepository
import com.genesys.core.model.notebook.NotebookStroke
import javax.inject.Inject

/**
 * Room-backed implementation of [NotebookStrokeRepository].
 */
class NotebookStrokeRepositoryImpl @Inject constructor(
    private val strokeDao: NotebookStrokeDao
) : NotebookStrokeRepository {

    override suspend fun create(stroke: NotebookStroke): Long =
        strokeDao.create(stroke.toEntity())

    override suspend fun create(strokes: List<NotebookStroke>) =
        strokeDao.create(strokes.map { it.toEntity() })

    override suspend fun update(stroke: NotebookStroke) =
        strokeDao.update(stroke.toEntity())

    override suspend fun update(strokes: List<NotebookStroke>) =
        strokeDao.update(strokes.map { it.toEntity() })

    override suspend fun deleteAll(ids: List<String>) =
        strokeDao.deleteAll(ids)

    override suspend fun getById(strokeId: String): NotebookStroke =
        strokeDao.getById(strokeId).toDomain()
}
