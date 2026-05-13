package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.core.model.notebook.Notebook
import com.genesys.core.model.notebook.NotebookPage
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveNotebookInFolderUseCase @Inject constructor(
    private val notebookRepository: NotebookRepository
) {
    operator fun invoke(folderId: String? = null): Flow<List<Notebook>> {
        return notebookRepository.observeInFolder(folderId)
    }
}
