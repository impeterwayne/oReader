package com.genesys.core.database.converters

import androidx.room.TypeConverter
import com.genesys.core.model.notebook.NotebookPen
import com.genesys.core.model.notebook.NotebookStrokePoint
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class NotebookTypeConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>): String = gson.toJson(value)

    @TypeConverter
    fun toStringList(value: String): List<String> = runCatching {
        gson.fromJson<List<String>>(value, object : TypeToken<List<String>>() {}.type)
    }.getOrDefault(emptyList())

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
