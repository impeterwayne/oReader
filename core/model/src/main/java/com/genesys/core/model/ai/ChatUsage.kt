package com.genesys.core.model.ai

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ChatUsage(
    @SerializedName("prompt_tokens")
    val promptTokens: Int? = null,
    @SerializedName("completion_tokens")
    val completionTokens: Int? = null,
    @SerializedName("total_tokens")
    val totalTokens: Int? = null
)
