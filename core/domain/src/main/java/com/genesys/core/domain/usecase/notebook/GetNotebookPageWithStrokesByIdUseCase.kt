package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.domain.repository.notebook.PageWithStrokes
import javax.inject.Inject

class GetNotebookPageWithStrokesByIdUseCase @Inject constructor(
    private val notebookPageRepository: NotebookPageRepository
) {
    suspend operator fun invoke(pageId: String): PageWithStrokes {
        return notebookPageRepository.getWithStrokesById(pageId)
    }
}
