package com.genesys.core.data.repository.notebook

import com.genesys.core.domain.repository.notebook.NotebookKeyValueRepository
import com.genesys.core.model.notebook.NotebookKeyValue
import com.genesys.core.model.notebook.NotebookSettings
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

/**
 * High-level proxy for reading and writing [NotebookSettings].
 *
 * Ported from Notable's KvProxy + GlobalAppSettings pattern. Keeps an
 * in-memory snapshot for low-latency UI reads, and persists changes to the
 * notebook key-value store (Room) asynchronously.
 */
@Singleton
class NotebookSettingsManager @Inject constructor(
    private val kvRepository: NotebookKeyValueRepository
) {
    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val adapter: JsonAdapter<NotebookSettings> =
        moshi.adapter(NotebookSettings::class.java)

    private val _current = MutableStateFlow(NotebookSettings())

    /** Observable snapshot of current settings, updated after every write. */
    val current: Flow<NotebookSettings> = _current.asStateFlow()

    /** Synchronous read of the latest in-memory settings snapshot. */
    val snapshot: NotebookSettings get() = _current.value

    /**
     * Load persisted settings into the in-memory snapshot.
     * Call once during app startup (e.g. from Application.onCreate or a Hilt initialiser).
     */
    suspend fun load() = withContext(Dispatchers.IO) {
        val kv = kvRepository.get(NotebookSettings.KV_KEY) ?: return@withContext
        try {
            val settings = adapter.fromJson(kv.value) ?: return@withContext
            _current.value = settings
        } catch (e: Exception) {
            Timber.e(e, "Failed to deserialise NotebookSettings")
        }
    }

    /** Persist [settings] to storage and update the in-memory snapshot. */
    suspend fun save(settings: NotebookSettings) {
        _current.value = settings
        withContext(Dispatchers.IO) {
            try {
                val json = adapter.toJson(settings)
                kvRepository.set(NotebookKeyValue(NotebookSettings.KV_KEY, json))
            } catch (e: Exception) {
                Timber.e(e, "Failed to persist NotebookSettings")
            }
        }
    }

    /** Observe settings reactively from the KV store (for cross-process sync). */
    fun observe(): Flow<NotebookSettings> =
        kvRepository.observe(NotebookSettings.KV_KEY).map { kv ->
            if (kv == null) return@map NotebookSettings()
            try {
                adapter.fromJson(kv.value) ?: NotebookSettings()
            } catch (e: Exception) {
                Timber.e(e, "Failed to deserialise observed NotebookSettings")
                NotebookSettings()
            }
        }
}
