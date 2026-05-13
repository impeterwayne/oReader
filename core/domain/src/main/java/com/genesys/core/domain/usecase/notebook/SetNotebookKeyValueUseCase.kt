package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookKeyValueRepository
import com.genesys.core.model.notebook.NotebookKeyValue
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SetNotebookKeyValueUseCase @Inject constructor(
    private val notebookKeyValueRepository: NotebookKeyValueRepository
) {
    suspend operator fun invoke(entry: NotebookKeyValue): Unit {
        return notebookKeyValueRepository.set(entry)
    }
}
