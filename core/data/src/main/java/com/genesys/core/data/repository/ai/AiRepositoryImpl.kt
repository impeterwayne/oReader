package com.genesys.core.data.repository.ai

import com.genesys.core.common.base.Result
import com.genesys.core.domain.repository.ai.AiRepository
import com.genesys.core.model.ai.AiModel
import com.genesys.core.model.ai.ChatCompletionRequest
import com.genesys.core.model.ai.ChatCompletionResponse
import com.genesys.core.model.ai.ChatMessage
import com.genesys.core.network.service.ApiService
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AiRepository {

    override suspend fun getModels(): Result<List<AiModel>> = withContext(Dispatchers.IO) {
        try {
            when (val response = apiService.getModels()) {
                is ApiResponse.Success -> Result.Success(response.data.data)
                is ApiResponse.Failure.Error -> Result.Error()
                is ApiResponse.Failure.Exception -> Result.Error(exception = Exception(response.throwable))
            }
        } catch (error: Exception) {
            Timber.e(error, "Failed to fetch AI models")
            Result.Error(exception = error)
        }
    }

    override suspend fun createChatCompletion(
        model: String,
        messages: List<ChatMessage>
    ): Result<ChatCompletionResponse> = withContext(Dispatchers.IO) {
        try {
            when (
                val response = apiService.createChatCompletion(
                    ChatCompletionRequest(
                        model = model,
                        messages = messages
                    )
                )
            ) {
                is ApiResponse.Success -> Result.Success(response.data)
                is ApiResponse.Failure.Error -> Result.Error()
                is ApiResponse.Failure.Exception -> Result.Error(exception = Exception(response.throwable))
            }
        } catch (error: Exception) {
            Timber.e(error, "Failed to create AI chat completion")
            Result.Error(exception = error)
        }
    }
}
