package com.genesys.core.model.library

sealed class OpenBookResult {
    data class Available(val filePath: String, val isStaged: Boolean) : OpenBookResult()
    data class Unavailable(val reason: String) : OpenBookResult()
}
