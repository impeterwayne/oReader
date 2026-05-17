package com.genesys.core.model.ai

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ChatCompletionResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("object")
    val `object`: String? = null,
    @SerializedName("created")
    val created: Long? = null,
    @SerializedName("model")
    val model: String? = null,
    @SerializedName("choices")
    val choices: List<ChatChoice> = emptyList(),
    @SerializedName("usage")
    val usage: ChatUsage? = null
)
