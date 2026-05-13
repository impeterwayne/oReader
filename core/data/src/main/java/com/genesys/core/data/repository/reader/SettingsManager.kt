package com.genesys.core.data.repository.reader

import com.genesys.core.domain.repository.notebook.NotebookKeyValueRepository
import com.genesys.core.domain.repository.reader.SettingsRepository
import com.genesys.core.model.notebook.NotebookKeyValue
import com.genesys.core.model.reader.ReaderScanFolder
import com.genesys.core.model.reader.ReaderSettings
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsManager @Inject constructor(
    private val kvRepository: NotebookKeyValueRepository
) : SettingsRepository {
    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val adapter: JsonAdapter<ReaderSettings> =
        moshi.adapter(ReaderSettings::class.java)

    private val _current = MutableStateFlow(ReaderSettings())

    override val current: Flow<ReaderSettings> = _current.asStateFlow()

    override val snapshot: ReaderSettings get() = _current.value

    override suspend fun load() = withContext(Dispatchers.IO) {
        val kv = kvRepository.get(ReaderSettings.KV_KEY) ?: return@withContext
        _current.value = parseSettings(kv.value)
    }

    override suspend fun save(readerSettings: ReaderSettings) {
        val normalized = readerSettings.copy(scanFolders = normalizeFolders(readerSettings.scanFolders))
        _current.value = normalized
        withContext(Dispatchers.IO) {
            try {
                kvRepository.set(NotebookKeyValue(ReaderSettings.KV_KEY, adapter.toJson(normalized)))
            } catch (error: Exception) {
                Timber.e(error, "Failed to persist Settings")
            }
        }
    }

    override fun observe(): Flow<ReaderSettings> =
        kvRepository.observe(ReaderSettings.KV_KEY).map { kv ->
            kv?.value?.let(::parseSettings) ?: ReaderSettings()
        }

    override fun listScanFolders(): List<ReaderScanFolder> = snapshot.scanFolders

    override suspend fun addScanFolder(uri: String, label: String): ReaderSettings {
        val cleanUri = uri.trim()
        if (cleanUri.isBlank()) {
            return snapshot
        }

        val now = System.currentTimeMillis()
        val existing = snapshot.scanFolders.firstOrNull { it.uri == cleanUri }
        val folder = if (existing == null) {
            ReaderScanFolder(
                uri = cleanUri,
                label = label.trim(),
                addedAt = now,
                updatedAt = now
            )
        } else {
            existing.copy(
                label = label.trim().ifBlank { existing.label },
                updatedAt = now
            )
        }

        return updateFolders(snapshot.scanFolders.filterNot { it.uri == cleanUri } + folder)
    }

    override suspend fun removeScanFolder(uri: String): ReaderSettings {
        val cleanUri = uri.trim()
        return updateFolders(snapshot.scanFolders.filterNot { it.uri == cleanUri })
    }

    override suspend fun updateScanFolder(uri: String, label: String): ReaderSettings {
        val cleanUri = uri.trim()
        val now = System.currentTimeMillis()
        return updateFolders(
            snapshot.scanFolders.map { folder ->
                if (folder.uri == cleanUri) {
                    folder.copy(label = label.trim(), updatedAt = now)
                } else {
                    folder
                }
            }
        )
    }

    private suspend fun updateFolders(folders: List<ReaderScanFolder>): ReaderSettings {
        val settings = snapshot.copy(scanFolders = normalizeFolders(folders))
        save(settings)
        return settings
    }

    private fun parseSettings(json: String): ReaderSettings {
        return try {
            adapter.fromJson(json)?.let { settings ->
                settings.copy(scanFolders = normalizeFolders(settings.scanFolders))
            } ?: ReaderSettings()
        } catch (error: Exception) {
            Timber.e(error, "Failed to deserialise Settings")
            ReaderSettings()
        }
    }

    private fun normalizeFolders(folders: List<ReaderScanFolder>): List<ReaderScanFolder> {
        return folders
            .asReversed()
            .distinctBy { it.uri.trim() }
            .asReversed()
            .mapNotNull { folder ->
                val uri = folder.uri.trim()
                if (uri.isBlank()) {
                    null
                } else {
                    val addedAt = folder.addedAt.takeIf { it > 0L } ?: folder.updatedAt
                    folder.copy(
                        uri = uri,
                        label = folder.label.trim(),
                        addedAt = addedAt.takeIf { it > 0L } ?: 0L,
                        updatedAt = folder.updatedAt.takeIf { it > 0L } ?: addedAt
                    )
                }
            }
    }
}
