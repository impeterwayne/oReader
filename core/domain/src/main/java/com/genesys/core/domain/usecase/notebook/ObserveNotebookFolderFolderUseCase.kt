package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookFolderRepository
import com.genesys.core.model.notebook.NotebookFolder
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveNotebookFolderFolderUseCase @Inject constructor(
    private val notebookFolderRepository: NotebookFolderRepository
) {
    operator fun invoke(folderId: String): Flow<NotebookFolder?> {
        return notebookFolderRepository.observeFolder(folderId)
    }
}
