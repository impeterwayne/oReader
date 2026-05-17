package com.genesys.core.domain.usecase.ai

import com.genesys.core.domain.repository.ai.AiSettingsRepository
import com.genesys.core.model.ai.AiSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAiSettingsUseCase @Inject constructor(
    private val aiSettingsRepository: AiSettingsRepository
) {
    operator fun invoke(): Flow<AiSettings> {
        return aiSettingsRepository.observeSettings()
    }
}
