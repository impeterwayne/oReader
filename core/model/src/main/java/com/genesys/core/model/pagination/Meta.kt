package com.genesys.core.model.pagination

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Meta(
    @SerializedName("pagination")
    val pagination: Pagination = Pagination()
)
