package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookImageRepository
import com.genesys.core.model.notebook.NotebookImage
import javax.inject.Inject

class UpdateNotebookImageUseCase @Inject constructor(
    private val notebookImageRepository: NotebookImageRepository
) {
    suspend operator fun invoke(image: NotebookImage): Unit {
        return notebookImageRepository.update(image)
    }
}
