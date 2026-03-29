package com.genesys.core.database.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import com.genesys.core.database.entity.NotebookImageEntity
import com.genesys.core.database.entity.NotebookPageEntity
import com.genesys.core.database.entity.NotebookStrokeEntity
import kotlinx.coroutines.flow.Flow

data class NotebookPageWithStrokesEntity(
    @Embedded val page: NotebookPageEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pageId",
        entity = NotebookStrokeEntity::class
    )
    val strokes: List<NotebookStrokeEntity>
)

data class NotebookPageWithImagesEntity(
    @Embedded val page: NotebookPageEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pageId",
        entity = NotebookImageEntity::class
    )
    val images: List<NotebookImageEntity>
)

@Dao
interface NotebookPageDao {
    @Query("SELECT * FROM Page WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<String>): List<NotebookPageEntity>

    @Query("SELECT * FROM Page WHERE id = :pageId")
    suspend fun getById(pageId: String): NotebookPageEntity?

    @Query("SELECT * FROM Page WHERE id = :pageId")
    fun observeById(pageId: String): Flow<NotebookPageEntity?>

    @Transaction
    @Query("SELECT * FROM Page WHERE id = :pageId")
    suspend fun getPageWithStrokesById(pageId: String): NotebookPageWithStrokesEntity

    @Transaction
    @Query("SELECT * FROM Page WHERE id = :pageId")
    suspend fun getPageWithImagesById(pageId: String): NotebookPageWithImagesEntity

    @Query("UPDATE Page SET scroll = :scroll WHERE id = :pageId")
    suspend fun updateScroll(pageId: String, scroll: Int)

    @Query("SELECT * FROM Page WHERE notebookId IS NULL AND parentFolderId IS :folderId")
    fun observeStandalonePages(folderId: String?): Flow<List<NotebookPageEntity>>

    @Insert
    suspend fun create(page: NotebookPageEntity): Long

    @Update
    suspend fun update(page: NotebookPageEntity)

    @Query("DELETE FROM Page WHERE id = :pageId")
    suspend fun delete(pageId: String)
}
