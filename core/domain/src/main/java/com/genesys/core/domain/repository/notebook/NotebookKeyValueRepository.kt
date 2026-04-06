package com.genesys.core.domain.repository.notebook

import com.genesys.core.model.notebook.NotebookKeyValue
import kotlinx.coroutines.flow.Flow

/**
 * Repository contract for the notebook key-value store.
 *
 * Ported from Notable's KvRepository. Used for persisting app settings,
 * editor state, and other arbitrary serialised blobs.
 */
interface NotebookKeyValueRepository {

    suspend fun get(key: String): NotebookKeyValue?

    fun observe(key: String): Flow<NotebookKeyValue?>

    suspend fun set(entry: NotebookKeyValue)

    suspend fun delete(key: String)
}
