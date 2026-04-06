package com.genesys.core.domain.repository.notebook

import com.genesys.core.model.notebook.NotebookStroke

/**
 * Repository contract for stroke persistence.
 *
 * Ported from Notable's StrokeRepository.
 */
interface NotebookStrokeRepository {

    suspend fun create(stroke: NotebookStroke): Long

    suspend fun create(strokes: List<NotebookStroke>)

    suspend fun update(stroke: NotebookStroke)

    suspend fun update(strokes: List<NotebookStroke>)

    suspend fun deleteAll(ids: List<String>)

    suspend fun getById(strokeId: String): NotebookStroke
}
