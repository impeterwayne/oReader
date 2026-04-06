package com.genesys.feature.notebook.editor.utils

import androidx.core.graphics.toRect
import com.genesys.core.model.notebook.NotebookStroke
import com.genesys.core.model.notebook.NotebookStrokePoint
import com.genesys.feature.notebook.editor.PageView
import android.util.Log as ShipBookLog


private val log = "draw" // Logger tag

// touchpoints are in page coordinates
fun handleDraw(
    page: PageView,
    historyBucket: MutableList<String>,
    strokeSize: Float,
    color: Int,
    pen: Pen,
    touchPoints: List<StrokePoint>
) {
    try {
        val boundingBox = calculateBoundingBox(touchPoints) { Pair(it.x, it.y) }

        //move rectangle
        boundingBox.inset(-strokeSize, -strokeSize)

        val stroke = Stroke(
            size = strokeSize,
            pen = pen,
            pageId = page.currentPageId,
            top = boundingBox.top,
            bottom = boundingBox.bottom,
            left = boundingBox.left,
            right = boundingBox.right,
            points = touchPoints,
            color = color,
            maxPressure = OnyxSdkCompat.getMaxTouchPressureOrDefault().toInt()
        )
        page.addStrokes(listOf(stroke))
        // this is causing lagging and crushing, neo pens are not good
        page.drawAreaPageCoordinates(strokeBounds(stroke).toRect())
        historyBucket.add(stroke.id)
    } catch (e: Exception) {
        log.e("Handle Draw: An error occurred while handling the drawing: ${e.message}")
    }
}
