package com.genesys.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.genesys.core.model.notebook.NotebookPen
import com.genesys.core.model.notebook.NotebookStrokePoint
import java.util.Date

@Entity(
    tableName = "Stroke",
    foreignKeys = [
        ForeignKey(
            entity = NotebookPageEntity::class,
            parentColumns = ["id"],
            childColumns = ["pageId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NotebookStrokeEntity(
    @PrimaryKey
    val id: String,
    val size: Float,
    val pen: NotebookPen,
    @ColumnInfo(defaultValue = "0xFF000000")
    val color: Int,
    @ColumnInfo(defaultValue = "4096")
    val maxPressure: Int,
    val top: Float,
    val bottom: Float,
    val left: Float,
    val right: Float,
    val points: List<NotebookStrokePoint>,
    @ColumnInfo(index = true)
    val pageId: String,
    val createdAt: Date,
    val updatedAt: Date
)
