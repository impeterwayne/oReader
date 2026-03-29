package com.genesys.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "Notebook",
    foreignKeys = [
        ForeignKey(
            entity = NotebookFolderEntity::class,
            parentColumns = ["id"],
            childColumns = ["parentFolderId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NotebookEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val openPageId: String?,
    val pageIds: List<String>,
    @ColumnInfo(index = true)
    val parentFolderId: String?,
    @ColumnInfo(defaultValue = "blank")
    val defaultBackground: String,
    @ColumnInfo(defaultValue = "native")
    val defaultBackgroundType: String,
    val linkedExternalUri: String?,
    val createdAt: Date,
    val updatedAt: Date
)
