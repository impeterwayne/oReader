package com.genesys.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "Image",
    foreignKeys = [
        ForeignKey(
            entity = NotebookPageEntity::class,
            parentColumns = ["id"],
            childColumns = ["pageId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NotebookImageEntity(
    @PrimaryKey
    val id: String,
    val x: Int,
    val y: Int,
    val height: Int,
    val width: Int,
    val uri: String?,
    @ColumnInfo(index = true)
    val pageId: String,
    val createdAt: Date,
    val updatedAt: Date
)
