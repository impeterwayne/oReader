package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.model.notebook.NotebookImage
import com.genesys.core.model.notebook.NotebookPage
import com.genesys.core.model.notebook.NotebookStroke
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UpdateNotebookPageUseCase @Inject constructor(
    private val notebookPageRepository: NotebookPageRepository
) {
    suspend operator fun invoke(page: NotebookPage): Unit {
        return notebookPageRepository.update(page)
    }
}
