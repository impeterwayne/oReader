package com.genesys.core.common.base

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val msg: String? = null, val exception: Exception? = null) : Result<Nothing>()
    class Loading : Result<Nothing>()
    class Initial : Result<Nothing>()
}
