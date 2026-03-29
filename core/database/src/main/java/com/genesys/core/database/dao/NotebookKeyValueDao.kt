package com.genesys.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.genesys.core.database.entity.NotebookKeyValueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotebookKeyValueDao {
    @Query("SELECT * FROM Kv WHERE `key` = :key")
    suspend fun get(key: String): NotebookKeyValueEntity?

    @Query("SELECT * FROM Kv WHERE `key` = :key")
    fun observe(key: String): Flow<NotebookKeyValueEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun set(entry: NotebookKeyValueEntity)

    @Query("DELETE FROM Kv WHERE `key` = :key")
    suspend fun delete(key: String)
}
