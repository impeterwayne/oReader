package com.genesys.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kv")
data class NotebookKeyValueEntity(
    @PrimaryKey
    val key: String,
    val value: String
)
