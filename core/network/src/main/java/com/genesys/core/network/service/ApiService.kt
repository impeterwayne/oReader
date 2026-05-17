package com.genesys.core.network.service

import com.genesys.core.model.ai.ChatCompletionRequest
import com.genesys.core.model.ai.ChatCompletionResponse
import com.genesys.core.model.ai.ModelsResponse
import com.skydoves.sandwich.ApiResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Streaming
import retrofit2.http.Url

interface ApiService {

    @GET("models")
    suspend fun getModels(): ApiResponse<ModelsResponse>

    @POST("chat/completions")
    suspend fun createChatCompletion(
        @Body request: ChatCompletionRequest
    ): ApiResponse<ChatCompletionResponse>

    @Streaming
    @GET
    suspend fun downloadFile(@Url fileUrl: String): ResponseBody

}
