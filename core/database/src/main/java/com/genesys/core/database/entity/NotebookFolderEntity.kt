package com.genesys.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "Folder",
    foreignKeys = [
        ForeignKey(
            entity = NotebookFolderEntity::class,
            parentColumns = ["id"],
            childColumns = ["parentFolderId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NotebookFolderEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    @ColumnInfo(index = true)
    val parentFolderId: String?,
    val createdAt: Date,
    val updatedAt: Date
)
