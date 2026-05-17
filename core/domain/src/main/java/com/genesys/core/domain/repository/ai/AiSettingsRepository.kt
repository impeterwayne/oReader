package com.genesys.core.domain.repository.ai

import com.genesys.core.model.ai.AiSettings
import kotlinx.coroutines.flow.Flow

interface AiSettingsRepository {
    fun observeSettings(): Flow<AiSettings>
    suspend fun saveSettings(settings: AiSettings)
    suspend fun getSettings(): AiSettings
}
