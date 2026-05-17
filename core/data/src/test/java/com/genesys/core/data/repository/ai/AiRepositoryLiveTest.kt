package com.genesys.core.data.repository.ai

import com.genesys.core.common.base.Result
import com.genesys.core.model.ai.AiModel
import com.genesys.core.model.ai.ChatMessage
import com.genesys.core.network.BuildConfig
import com.genesys.core.network.service.ApiService
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AiRepositoryLiveTest {

    private val apiService: ApiService by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(loggingInterceptor)
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .build()

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    private val repository: AiRepositoryImpl by lazy {
        AiRepositoryImpl(apiService)
    }

    @Test
    fun getModels_returnsRealModels_fromLiveApi() = runBlocking {
        when (val result = repository.getModels()) {
            is Result.Success -> {
                val models = result.data
                assertTrue("Expected at least one model from live API", models.isNotEmpty())
                assertTrue("Expected every model id to be non-blank", models.all { it.id.isNotBlank() })
            }
            is Result.Error -> fail(buildFailureMessage("GET /models", result))
            else -> fail("Unexpected result type for GET /models: ${result::class.java.simpleName}")
        }
    }

    @Test
    fun createChatCompletion_returnsAssistantReply_fromLiveApi() = runBlocking {
        val models: List<AiModel> = when (val modelsResult = repository.getModels()) {
            is Result.Success -> modelsResult.data
            is Result.Error -> throw AssertionError(buildFailureMessage("GET /models", modelsResult))
            else -> throw AssertionError("Unexpected result type for GET /models: ${modelsResult::class.java.simpleName}")
        }

        val firstModel = models.firstOrNull()
            ?: throw AssertionError("Live API returned success for /models but no models were available")
        val selectedModel = firstModel.id

        when (
            val result = repository.createChatCompletion(
                model = selectedModel,
                messages = listOf(
                    ChatMessage(
                        role = "user",
                        content = "Reply with exactly the word: pong"
                    )
                )
            )
        ) {
            is Result.Success -> {
                val response = result.data
                assertEqualsIgnoreCaseTrimmed("pong", response.choices.firstOrNull()?.message?.content)
                assertNotNull(response.id)
                assertNotNull(response.model)
            }
            is Result.Error -> fail(buildFailureMessage("POST /chat/completions", result))
            else -> fail("Unexpected result type for POST /chat/completions: ${result::class.java.simpleName}")
        }
    }

    private fun buildFailureMessage(endpoint: String, result: Result.Error): String {
        val exceptionText = result.exception?.let { "${it::class.java.simpleName}: ${it.message}" }
        return buildString {
            append("Live API call failed for ")
            append(endpoint)
            append(" using base URL ")
            append(BuildConfig.BASE_URL)
            append('.')
            if (!result.msg.isNullOrBlank()) {
                append(" msg=")
                append(result.msg)
                append('.')
            }
            if (!exceptionText.isNullOrBlank()) {
                append(" exception=")
                append(exceptionText)
                append('.')
            }
            append(" Check the test logs for raw HTTP status/body.")
        }
    }

    private fun assertEqualsIgnoreCaseTrimmed(expected: String, actual: String?) {
        assertNotNull(actual)
        assertTrue(actual!!.trim().equals(expected, ignoreCase = true))
    }
}
