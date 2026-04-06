package com.genesys.feature.notebook.editor.utils

import com.genesys.core.model.notebook.NotebookPen
import com.onyx.android.sdk.pen.style.StrokeStyle

/**
 * Maps a [NotebookPen] to the corresponding Onyx SDK [StrokeStyle] constant.
 */
fun penToStroke(pen: NotebookPen): Int {
    return when (pen) {
        NotebookPen.BALLPEN -> StrokeStyle.PENCIL
        NotebookPen.REDBALLPEN -> StrokeStyle.PENCIL
        NotebookPen.GREENBALLPEN -> StrokeStyle.PENCIL
        NotebookPen.BLUEBALLPEN -> StrokeStyle.PENCIL
        NotebookPen.PENCIL -> StrokeStyle.CHARCOAL
        NotebookPen.BRUSH -> StrokeStyle.NEO_BRUSH
        NotebookPen.MARKER -> StrokeStyle.MARKER
        NotebookPen.FOUNTAIN -> StrokeStyle.FOUNTAIN
        NotebookPen.DASHED -> StrokeStyle.DASH
    }
}
