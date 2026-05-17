package com.genesys.core.model.ai

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ChatChoice(
    @SerializedName("index")
    val index: Int? = null,
    @SerializedName("message")
    val message: ChatMessage? = null,
    @SerializedName("finish_reason")
    val finishReason: String? = null
)
