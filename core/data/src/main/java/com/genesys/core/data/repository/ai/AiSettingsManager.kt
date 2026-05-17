package com.genesys.core.data.repository.ai

import com.genesys.core.common.extension.fromJsonType
import com.genesys.core.domain.repository.ai.AiSettingsRepository
import com.genesys.core.domain.repository.notebook.NotebookKeyValueRepository
import com.genesys.core.model.ai.AiSettings
import com.genesys.core.model.notebook.NotebookKeyValue
import com.genesys.core.network.BuildConfig
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AiSettingsManager @Inject constructor(
    private val kvRepository: NotebookKeyValueRepository
) : AiSettingsRepository {
    private val gson = Gson()
    
    companion object {
        const val KV_KEY = "ai_settings"
    }

    override fun observeSettings(): Flow<AiSettings> {
        return kvRepository.observe(KV_KEY).map { kv ->
            if (kv != null) {
                try {
                    gson.fromJsonType<AiSettings>(kv.value)
                } catch (e: Exception) {
                    defaultSettings()
                }
            } else {
                defaultSettings()
            }
        }
    }

    override suspend fun saveSettings(settings: AiSettings) {
        withContext(Dispatchers.IO) {
            try {
                kvRepository.set(NotebookKeyValue(KV_KEY, gson.toJson(settings)))
            } catch (e: Exception) {
                Timber.e(e, "Failed to persist AiSettings")
            }
        }
    }

    override suspend fun getSettings(): AiSettings = withContext(Dispatchers.IO) {
        val kv = kvRepository.get(KV_KEY) ?: return@withContext defaultSettings()
        try {
            gson.fromJsonType<AiSettings>(kv.value)
        } catch (e: Exception) {
            defaultSettings()
        }
    }

    private fun defaultSettings(): AiSettings {
        return AiSettings(
            endpoint = BuildConfig.BASE_URL,
            apiKey = BuildConfig.API_KEY
        )
    }
}
