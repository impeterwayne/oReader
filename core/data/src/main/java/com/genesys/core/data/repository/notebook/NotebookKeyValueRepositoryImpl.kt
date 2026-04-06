package com.genesys.core.data.repository.notebook

import com.genesys.core.database.dao.NotebookKeyValueDao
import com.genesys.core.database.entity.mapper.toDomain
import com.genesys.core.database.entity.mapper.toEntity
import com.genesys.core.domain.repository.notebook.NotebookKeyValueRepository
import com.genesys.core.model.notebook.NotebookKeyValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Room-backed implementation of [NotebookKeyValueRepository].
 */
class NotebookKeyValueRepositoryImpl @Inject constructor(
    private val kvDao: NotebookKeyValueDao
) : NotebookKeyValueRepository {

    override suspend fun get(key: String): NotebookKeyValue? =
        withContext(Dispatchers.IO) {
            kvDao.get(key)?.toDomain()
        }

    override fun observe(key: String): Flow<NotebookKeyValue?> =
        kvDao.observe(key).map { it?.toDomain() }

    override suspend fun set(entry: NotebookKeyValue) =
        withContext(Dispatchers.IO) {
            kvDao.set(entry.toEntity())
        }

    override suspend fun delete(key: String) =
        withContext(Dispatchers.IO) {
            kvDao.delete(key)
        }
}
