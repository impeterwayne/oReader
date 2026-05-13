package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.core.model.notebook.Notebook
import com.genesys.core.model.notebook.NotebookPage
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveNotebookByIdUseCase @Inject constructor(
    private val notebookRepository: NotebookRepository
) {
    operator fun invoke(notebookId: String): Flow<Notebook?> {
        return notebookRepository.observeById(notebookId)
    }
}
