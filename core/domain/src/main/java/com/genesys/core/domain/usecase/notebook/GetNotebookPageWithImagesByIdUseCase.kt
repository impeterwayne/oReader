package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.domain.repository.notebook.PageWithImages
import javax.inject.Inject

class GetNotebookPageWithImagesByIdUseCase @Inject constructor(
    private val notebookPageRepository: NotebookPageRepository
) {
    suspend operator fun invoke(pageId: String): PageWithImages {
        return notebookPageRepository.getWithImagesById(pageId)
    }
}
