package com.genesys.core.model.ai

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ChatMessage(
    @SerializedName("role")
    val role: String,
    @SerializedName("content")
    val content: String
)
