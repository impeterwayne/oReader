package com.genesys.core.model.notebook

data class NotebookStrokePoint(
    val x: Float,
    val y: Float,
    val pressure: Float? = null,
    val tiltX: Int? = null,
    val tiltY: Int? = null,
    val dt: UShort? = null
)
