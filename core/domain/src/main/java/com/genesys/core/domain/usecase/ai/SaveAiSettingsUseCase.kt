package com.genesys.core.domain.usecase.ai

import com.genesys.core.domain.repository.ai.AiSettingsRepository
import com.genesys.core.model.ai.AiSettings
import javax.inject.Inject

class SaveAiSettingsUseCase @Inject constructor(
    private val aiSettingsRepository: AiSettingsRepository
) {
    suspend operator fun invoke(settings: AiSettings) {
        aiSettingsRepository.saveSettings(settings)
    }
}
