package com.genesys.core.domain.repository.notebook

import com.genesys.core.model.notebook.NotebookImage

/**
 * Repository contract for notebook image persistence.
 *
 * Ported from Notable's ImageRepository.
 */
interface NotebookImageRepository {

    suspend fun create(image: NotebookImage): Long

    suspend fun create(images: List<NotebookImage>)

    suspend fun update(image: NotebookImage)

    suspend fun deleteAll(ids: List<String>)

    suspend fun getById(imageId: String): NotebookImage
}
