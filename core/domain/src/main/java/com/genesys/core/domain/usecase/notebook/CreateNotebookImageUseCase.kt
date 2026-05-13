package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookImageRepository
import com.genesys.core.model.notebook.NotebookImage
import javax.inject.Inject

class CreateNotebookImageUseCase @Inject constructor(
    private val notebookImageRepository: NotebookImageRepository
) {
    suspend operator fun invoke(images: List<NotebookImage>): Unit {
        return notebookImageRepository.create(images)
    }
}
