package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookStrokeRepository
import com.genesys.core.model.notebook.NotebookStroke
import javax.inject.Inject

class DeleteNotebookStrokeAllUseCase @Inject constructor(
    private val notebookStrokeRepository: NotebookStrokeRepository
) {
    suspend operator fun invoke(ids: List<String>): Unit {
        return notebookStrokeRepository.deleteAll(ids)
    }
}
