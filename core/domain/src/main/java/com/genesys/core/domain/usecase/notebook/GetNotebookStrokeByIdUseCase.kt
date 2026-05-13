package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookStrokeRepository
import com.genesys.core.model.notebook.NotebookStroke
import javax.inject.Inject

class GetNotebookStrokeByIdUseCase @Inject constructor(
    private val notebookStrokeRepository: NotebookStrokeRepository
) {
    suspend operator fun invoke(strokeId: String): NotebookStroke {
        return notebookStrokeRepository.getById(strokeId)
    }
}
