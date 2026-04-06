package com.genesys.core.data.repository.notebook

import com.genesys.core.database.dao.NotebookFolderDao
import com.genesys.core.database.entity.mapper.toDomain
import com.genesys.core.database.entity.mapper.toDomainFolders
import com.genesys.core.database.entity.mapper.toEntity
import com.genesys.core.domain.repository.notebook.NotebookFolderRepository
import com.genesys.core.model.notebook.NotebookFolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Room-backed implementation of [NotebookFolderRepository].
 */
class NotebookFolderRepositoryImpl @Inject constructor(
    private val folderDao: NotebookFolderDao
) : NotebookFolderRepository {

    override suspend fun create(folder: NotebookFolder) {
        folderDao.create(folder.toEntity())
    }

    override suspend fun update(folder: NotebookFolder) {
        folderDao.update(folder.toEntity())
    }

    override fun observeChildren(folderId: String?): Flow<List<NotebookFolder>> =
        folderDao.observeChildren(folderId).map { it.toDomainFolders() }

    override fun observeFolder(folderId: String): Flow<NotebookFolder?> =
        folderDao.observeFolder(folderId).map { it?.toDomain() }

    override suspend fun get(folderId: String): NotebookFolder? =
        folderDao.get(folderId)?.toDomain()

    override suspend fun getParent(folderId: String?): String? {
        if (folderId == null) return null
        return folderDao.get(folderId)?.parentFolderId
    }

    override suspend fun delete(id: String) {
        folderDao.delete(id)
    }
}
