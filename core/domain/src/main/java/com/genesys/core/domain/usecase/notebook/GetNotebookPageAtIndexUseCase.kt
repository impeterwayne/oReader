package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.core.model.notebook.Notebook
import com.genesys.core.model.notebook.NotebookPage
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetNotebookPageAtIndexUseCase @Inject constructor(
    private val notebookRepository: NotebookRepository
) {
    suspend operator fun invoke(notebookId: String, index: Int): String? {
        return notebookRepository.getPageAtIndex(notebookId, index)
    }
}
