package com.genesys.feature.notebook.editor.state

import com.genesys.core.model.notebook.NotebookImage
import com.genesys.core.model.notebook.NotebookStroke

data class ClipboardContent(
    val strokes: List<NotebookStroke> = emptyList(),
    val images: List<NotebookImage> = emptyList(),
)
