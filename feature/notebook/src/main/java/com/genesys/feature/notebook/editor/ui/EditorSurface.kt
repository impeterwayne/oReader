package com.genesys.feature.notebook.editor.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.genesys.feature.notebook.editor.PageView
import com.genesys.feature.notebook.editor.canvas.DrawCanvas
import com.genesys.feature.notebook.editor.state.EditorState
import com.genesys.feature.notebook.editor.state.History

@Composable
fun EditorSurface(
    state: EditorState,
    page: PageView,
    history: History
) {
    val coroutineScope = rememberCoroutineScope()

    AndroidView(
        factory = { ctx ->
            DrawCanvas(
                context = ctx,
                coroutineScope = coroutineScope,
                state = state,
                page = page,
                history = history
            ).apply {
                init()
                registerObservers()
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
