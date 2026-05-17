package com.genesys.core.data.repository.ai

import com.genesys.core.common.base.Result
import com.genesys.core.model.ai.AiModel
import com.genesys.core.model.ai.ChatCompletionRequest
import com.genesys.core.model.ai.ChatCompletionResponse
import com.genesys.core.model.ai.ChatMessage
import com.genesys.core.model.ai.ChatUsage
import com.genesys.core.model.ai.ChatChoice
import com.genesys.core.model.ai.ModelsResponse
import com.genesys.core.network.service.ApiService
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AiRepositoryImplTest {

    @Test
    fun getModels_returnsSuccess_whenApiReturnsSuccess() = runBlocking {
        val expectedModels = listOf(
            AiModel(id = "gpt-4o-mini"),
            AiModel(id = "gpt-4.1-mini")
        )
        val repository = AiRepositoryImpl(
            apiService = FakeApiService(
                modelsResponse = ApiResponse.Success(
                    ModelsResponse(data = expectedModels)
                )
            )
        )

        val result = repository.getModels()

        assertTrue(result is Result.Success)
        assertEquals(expectedModels, (result as Result.Success).data)
    }

    @Test
    fun getModels_returnsError_whenApiReturnsFailureError() = runBlocking {
        val repository = AiRepositoryImpl(
            apiService = FakeApiService(
                modelsResponse = ApiResponse.Failure.Error(payload = "bad request")
            )
        )

        val result = repository.getModels()

        assertTrue(result is Result.Error)
        assertNull((result as Result.Error).msg)
    }

    @Test
    fun createChatCompletion_returnsSuccess_whenApiReturnsSuccess() = runBlocking {
        val messages = listOf(ChatMessage(role = "user", content = "Hello"))
        val expectedResponse = ChatCompletionResponse(
            id = "chatcmpl_123",
            model = "gpt-4o-mini",
            choices = listOf(
                ChatChoice(
                    index = 0,
                    message = ChatMessage(role = "assistant", content = "Hi there")
                )
            ),
            usage = ChatUsage(totalTokens = 12)
        )
        val fakeService = FakeApiService(
            chatResponse = ApiResponse.Success(expectedResponse)
        )
        val repository = AiRepositoryImpl(fakeService)

        val result = repository.createChatCompletion(
            model = "gpt-4o-mini",
            messages = messages
        )

        assertTrue(result is Result.Success)
        assertEquals(expectedResponse, (result as Result.Success).data)
        assertNotNull(fakeService.lastChatRequest)
        assertEquals("gpt-4o-mini", fakeService.lastChatRequest?.model)
        assertEquals(messages, fakeService.lastChatRequest?.messages)
    }

    @Test
    fun createChatCompletion_returnsError_whenApiThrowsException() = runBlocking {
        val expectedException = IllegalStateException("network down")
        val repository = AiRepositoryImpl(
            apiService = FakeApiService(
                chatResponse = ApiResponse.exception(expectedException)
            )
        )

        val result = repository.createChatCompletion(
            model = "gpt-4o-mini",
            messages = listOf(ChatMessage(role = "user", content = "Hello"))
        )

        assertTrue(result is Result.Error)
        assertEquals(expectedException, (result as Result.Error).exception?.cause ?: result.exception)
    }

    private class FakeApiService(
        private val modelsResponse: ApiResponse<ModelsResponse> = ApiResponse.Success(ModelsResponse()),
        private val chatResponse: ApiResponse<ChatCompletionResponse> = ApiResponse.Success(ChatCompletionResponse())
    ) : ApiService {
        var lastChatRequest: ChatCompletionRequest? = null

        override suspend fun getModels(): ApiResponse<ModelsResponse> = modelsResponse

        override suspend fun createChatCompletion(request: ChatCompletionRequest): ApiResponse<ChatCompletionResponse> {
            lastChatRequest = request
            return chatResponse
        }

        override suspend fun downloadFile(fileUrl: String): ResponseBody {
            throw UnsupportedOperationException("Not needed in tests")
        }
    }
}
