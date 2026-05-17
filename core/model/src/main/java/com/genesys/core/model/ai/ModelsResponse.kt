package com.genesys.core.model.ai

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ModelsResponse(
    @SerializedName("object")
    val `object`: String? = null,
    @SerializedName("data")
    val data: List<AiModel> = emptyList()
)
