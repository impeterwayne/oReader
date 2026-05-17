package com.genesys.core.domain.repository.ai

import com.genesys.core.common.base.Result
import com.genesys.core.model.ai.AiModel
import com.genesys.core.model.ai.ChatCompletionResponse
import com.genesys.core.model.ai.ChatMessage

interface AiRepository {
    suspend fun getModels(): Result<List<AiModel>>

    suspend fun createChatCompletion(
        model: String,
        messages: List<ChatMessage>
    ): Result<ChatCompletionResponse>
}
