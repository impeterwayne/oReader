package com.genesys.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.genesys.core.database.entity.NotebookFolderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotebookFolderDao {
    @Query("SELECT * FROM Folder WHERE parentFolderId IS :folderId")
    fun observeChildren(folderId: String?): Flow<List<NotebookFolderEntity>>

    @Query("SELECT * FROM Folder WHERE id IS :folderId")
    fun observeFolder(folderId: String): Flow<NotebookFolderEntity?>

    @Query("SELECT * FROM Folder WHERE id IS :folderId")
    suspend fun get(folderId: String): NotebookFolderEntity?

    @Insert
    suspend fun create(folder: NotebookFolderEntity): Long

    @Update
    suspend fun update(folder: NotebookFolderEntity)

    @Query("DELETE FROM Folder WHERE id = :id")
    suspend fun delete(id: String)
}
