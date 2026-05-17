package com.genesys.core.domain.usecase.ai

import com.genesys.core.common.base.Result
import com.genesys.core.domain.repository.ai.AiRepository
import com.genesys.core.model.ai.AiModel
import javax.inject.Inject

class GetAiModelsUseCase @Inject constructor(
    private val aiRepository: AiRepository
) {
    suspend operator fun invoke(): Result<List<AiModel>> {
        return aiRepository.getModels()
    }
}
