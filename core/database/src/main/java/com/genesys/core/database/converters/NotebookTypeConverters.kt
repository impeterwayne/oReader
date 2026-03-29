package com.genesys.core.database.converters

import androidx.room.TypeConverter
import com.genesys.core.model.notebook.NotebookPen
import com.genesys.core.model.notebook.NotebookStrokePoint
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.util.Date

class NotebookTypeConverters {
    private val moshi = Moshi.Builder().build()
    private val stringListAdapter = moshi.adapter<List<String>>(
        Types.newParameterizedType(List::class.java, String::class.java)
    )

    @TypeConverter
    fun fromStringList(value: List<String>): String = stringListAdapter.toJson(value)

    @TypeConverter
    fun toStringList(value: String): List<String> = stringListAdapter.fromJson(value).orEmpty()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let(::Date)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun fromPen(value: NotebookPen): String = value.penName

    @TypeConverter
    fun toPen(value: String): NotebookPen = NotebookPen.fromName(value)

    @TypeConverter
    fun fromStrokePoints(points: List<NotebookStrokePoint>?): ByteArray {
        return if (points.isNullOrEmpty()) {
            ByteArray(0)
        } else {
            encodeNotebookStrokePoints(points)
        }
    }

    @TypeConverter
    fun toStrokePoints(bytes: ByteArray?): List<NotebookStrokePoint> {
        return if (bytes == null || bytes.isEmpty()) {
            emptyList()
        } else {
            decodeNotebookStrokePoints(bytes)
        }
    }
}
