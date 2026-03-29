package com.genesys.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.genesys.core.database.entity.NotebookStrokeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotebookStrokeDao {
    @Insert
    suspend fun create(stroke: NotebookStrokeEntity): Long

    @Insert
    suspend fun create(strokes: List<NotebookStrokeEntity>)

    @Update
    suspend fun update(stroke: NotebookStrokeEntity)

    @Update
    suspend fun update(strokes: List<NotebookStrokeEntity>)

    @Query("DELETE FROM Stroke WHERE id IN (:ids)")
    suspend fun deleteAll(ids: List<String>)

    @Query("SELECT * FROM Stroke WHERE id = :strokeId")
    suspend fun getById(strokeId: String): NotebookStrokeEntity

    @Query("SELECT * FROM Stroke WHERE pageId = :pageId")
    fun observeForPage(pageId: String): Flow<List<NotebookStrokeEntity>>
}
