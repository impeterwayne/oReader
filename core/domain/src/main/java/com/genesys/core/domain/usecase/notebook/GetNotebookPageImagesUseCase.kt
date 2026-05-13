package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.model.notebook.NotebookImage
import javax.inject.Inject

class GetNotebookPageImagesUseCase @Inject constructor(
    private val notebookPageRepository: NotebookPageRepository
) {
    suspend operator fun invoke(pageId: String): List<NotebookImage> {
        return notebookPageRepository.getWithImagesById(pageId).images
    }
}
