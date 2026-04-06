package com.genesys.core.data.repository.notebook

import com.genesys.core.database.dao.NotebookImageDao
import com.genesys.core.database.entity.mapper.toDomain
import com.genesys.core.database.entity.mapper.toEntity
import com.genesys.core.domain.repository.notebook.NotebookImageRepository
import com.genesys.core.model.notebook.NotebookImage
import javax.inject.Inject

/**
 * Room-backed implementation of [NotebookImageRepository].
 */
class NotebookImageRepositoryImpl @Inject constructor(
    private val imageDao: NotebookImageDao
) : NotebookImageRepository {

    override suspend fun create(image: NotebookImage): Long =
        imageDao.create(image.toEntity())

    override suspend fun create(images: List<NotebookImage>) =
        imageDao.create(images.map { it.toEntity() })

    override suspend fun update(image: NotebookImage) =
        imageDao.update(image.toEntity())

    override suspend fun deleteAll(ids: List<String>) =
        imageDao.deleteAll(ids)

    override suspend fun getById(imageId: String): NotebookImage =
        imageDao.getById(imageId).toDomain()
}
