package com.genesys.core.domain.usecase.notebook

import com.genesys.core.domain.repository.notebook.NotebookKeyValueRepository
import com.genesys.core.model.notebook.NotebookKeyValue
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetNotebookKeyValueUseCase @Inject constructor(
    private val notebookKeyValueRepository: NotebookKeyValueRepository
) {
    suspend operator fun invoke(key: String): NotebookKeyValue? {
        return notebookKeyValueRepository.get(key)
    }
}
