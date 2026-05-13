package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookStrokeRepository
import com.genesys.core.model.notebook.NotebookStroke
import javax.inject.Inject

class UpdateNotebookStrokeUseCase @Inject constructor(
    private val notebookStrokeRepository: NotebookStrokeRepository
) {
    suspend operator fun invoke(strokes: List<NotebookStroke>): Unit {
        return notebookStrokeRepository.update(strokes)
    }
}
