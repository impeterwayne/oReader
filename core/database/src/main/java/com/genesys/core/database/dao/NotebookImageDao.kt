package com.genesys.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.genesys.core.database.entity.NotebookImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotebookImageDao {
    @Insert
    suspend fun create(image: NotebookImageEntity): Long

    @Insert
    suspend fun create(images: List<NotebookImageEntity>)

    @Update
    suspend fun update(image: NotebookImageEntity)

    @Query("DELETE FROM Image WHERE id IN (:ids)")
    suspend fun deleteAll(ids: List<String>)

    @Query("SELECT * FROM Image WHERE id = :imageId")
    suspend fun getById(imageId: String): NotebookImageEntity

    @Query("SELECT * FROM Image WHERE pageId = :pageId")
    fun observeForPage(pageId: String): Flow<List<NotebookImageEntity>>
}
