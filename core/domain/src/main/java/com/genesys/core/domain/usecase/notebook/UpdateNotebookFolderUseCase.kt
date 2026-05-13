package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookFolderRepository
import com.genesys.core.model.notebook.NotebookFolder
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UpdateNotebookFolderUseCase @Inject constructor(
    private val notebookFolderRepository: NotebookFolderRepository
) {
    suspend operator fun invoke(folder: NotebookFolder): Unit {
        return notebookFolderRepository.update(folder)
    }
}
