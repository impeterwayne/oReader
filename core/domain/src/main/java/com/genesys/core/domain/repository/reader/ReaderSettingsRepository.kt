package com.genesys.core.domain.repository.reader

import com.genesys.core.model.reader.ReaderScanFolder
import com.genesys.core.model.reader.ReaderSettings
import kotlinx.coroutines.flow.Flow

interface ReaderSettingsRepository {
    val current: Flow<ReaderSettings>

    val snapshot: ReaderSettings

    suspend fun load()

    suspend fun save(settings: ReaderSettings)

    fun observe(): Flow<ReaderSettings>

    fun listScanFolders(): List<ReaderScanFolder>

    suspend fun addScanFolder(uri: String, label: String): ReaderSettings

    suspend fun removeScanFolder(uri: String): ReaderSettings

    suspend fun updateScanFolder(uri: String, label: String): ReaderSettings
}
