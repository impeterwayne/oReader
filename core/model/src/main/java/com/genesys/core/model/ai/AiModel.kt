package com.genesys.core.model.ai

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AiModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("object")
    val `object`: String? = null,
    @SerializedName("created")
    val created: Long? = null,
    @SerializedName("owned_by")
    val ownedBy: String? = null
)
