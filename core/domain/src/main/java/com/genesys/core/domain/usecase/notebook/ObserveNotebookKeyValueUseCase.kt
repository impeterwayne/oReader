package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookKeyValueRepository
import com.genesys.core.model.notebook.NotebookKeyValue
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveNotebookKeyValueUseCase @Inject constructor(
    private val notebookKeyValueRepository: NotebookKeyValueRepository
) {
    operator fun invoke(key: String): Flow<NotebookKeyValue?> {
        return notebookKeyValueRepository.observe(key)
    }
}
