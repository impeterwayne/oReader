package com.genesys.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.genesys.core.database.converters.NotebookTypeConverters
import com.genesys.core.database.dao.NotebookDao
import com.genesys.core.database.dao.NotebookFolderDao
import com.genesys.core.database.dao.NotebookImageDao
import com.genesys.core.database.dao.NotebookKeyValueDao
import com.genesys.core.database.dao.NotebookPageDao
import com.genesys.core.database.dao.NotebookStrokeDao
import com.genesys.core.database.entity.NotebookEntity
import com.genesys.core.database.entity.NotebookFolderEntity
import com.genesys.core.database.entity.NotebookImageEntity
import com.genesys.core.database.entity.NotebookKeyValueEntity
import com.genesys.core.database.entity.NotebookPageEntity
import com.genesys.core.database.entity.NotebookStrokeEntity

@Database(
    entities = [
        NotebookFolderEntity::class,
        NotebookEntity::class,
        NotebookPageEntity::class,
        NotebookStrokeEntity::class,
        NotebookImageEntity::class,
        NotebookKeyValueEntity::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(NotebookTypeConverters::class)
abstract class NotebookDatabase : RoomDatabase() {
    abstract fun notebookFolderDao(): NotebookFolderDao
    abstract fun notebookDao(): NotebookDao
    abstract fun notebookPageDao(): NotebookPageDao
    abstract fun notebookStrokeDao(): NotebookStrokeDao
    abstract fun notebookImageDao(): NotebookImageDao
    abstract fun notebookKeyValueDao(): NotebookKeyValueDao
}
