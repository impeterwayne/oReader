package com.genesys.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "Page",
    foreignKeys = [
        ForeignKey(
            entity = NotebookFolderEntity::class,
            parentColumns = ["id"],
            childColumns = ["parentFolderId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = NotebookEntity::class,
            parentColumns = ["id"],
            childColumns = ["notebookId"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class NotebookPageEntity(
    @PrimaryKey
    val id: String,
    val scroll: Int,
    @ColumnInfo(index = true)
    val notebookId: String?,
    @ColumnInfo(defaultValue = "blank")
    val background: String,
    @ColumnInfo(defaultValue = "native")
    val backgroundType: String,
    @ColumnInfo(index = true)
    val parentFolderId: String?,
    val createdAt: Date,
    val updatedAt: Date
)
