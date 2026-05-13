package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.model.notebook.NotebookPage
import javax.inject.Inject

class GetNotebookPagePageUseCase @Inject constructor(
    private val notebookPageRepository: NotebookPageRepository
) {
    suspend operator fun invoke(pageId: String): NotebookPage? {
        return notebookPageRepository.getById(pageId)
    }
}
