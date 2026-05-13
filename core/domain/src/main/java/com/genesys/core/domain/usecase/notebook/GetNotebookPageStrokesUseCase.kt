package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.model.notebook.NotebookStroke
import javax.inject.Inject

class GetNotebookPageStrokesUseCase @Inject constructor(
    private val notebookPageRepository: NotebookPageRepository
) {
    suspend operator fun invoke(pageId: String): List<NotebookStroke> {
        return notebookPageRepository.getWithStrokesById(pageId).strokes
    }
}
