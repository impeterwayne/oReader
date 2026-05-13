package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.model.notebook.NotebookImage
import com.genesys.core.model.notebook.NotebookPage
import com.genesys.core.model.notebook.NotebookStroke
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetNotebookPageByIdsUseCase @Inject constructor(
    private val notebookPageRepository: NotebookPageRepository
) {
    suspend operator fun invoke(ids: List<String>): List<NotebookPage> {
        return notebookPageRepository.getByIds(ids)
    }
}
