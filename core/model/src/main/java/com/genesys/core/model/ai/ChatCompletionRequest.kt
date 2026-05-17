package com.genesys.core.model.ai

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ChatCompletionRequest(
    @SerializedName("model")
    val model: String,
    @SerializedName("messages")
    val messages: List<ChatMessage>
)
