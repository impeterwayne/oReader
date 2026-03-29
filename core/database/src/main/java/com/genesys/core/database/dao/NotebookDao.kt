package com.genesys.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.genesys.core.database.entity.NotebookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotebookDao {
    @Query("SELECT * FROM Notebook WHERE parentFolderId IS :folderId")
    fun observeInFolder(folderId: String?): Flow<List<NotebookEntity>>

    @Query("SELECT * FROM Notebook WHERE id = :notebookId")
    fun observeById(notebookId: String): Flow<NotebookEntity?>

    @Query("SELECT * FROM Notebook WHERE id = :notebookId")
    suspend fun getById(notebookId: String): NotebookEntity?

    @Query("UPDATE Notebook SET openPageId = :pageId WHERE id = :notebookId")
    suspend fun setOpenPageId(notebookId: String, pageId: String)

    @Query("UPDATE Notebook SET pageIds = :pageIds WHERE id = :notebookId")
    suspend fun setPageIds(notebookId: String, pageIds: List<String>)

    @Insert
    suspend fun create(notebook: NotebookEntity): Long

    @Update
    suspend fun update(notebook: NotebookEntity)

    @Query("DELETE FROM Notebook WHERE id = :id")
    suspend fun delete(id: String)
}
