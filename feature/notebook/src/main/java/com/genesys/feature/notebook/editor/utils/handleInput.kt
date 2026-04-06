package com.genesys.feature.notebook.editor.utils

import androidx.compose.ui.geometry.Offset
import com.genesys.core.model.notebook.NotebookStrokePoint
import com.genesys.feature.notebook.editor.model.SimplePointF
import com.onyx.android.sdk.data.note.TouchPoint


fun copyInput(touchPoints: List<TouchPoint>, scroll: Offset, scale: Float): List<StrokePoint> {
    val points = touchPoints.map {
        it.toStrokePoint(scroll, scale)
    }
    return points
}


fun copyInputToSimplePointF(
    touchPoints: List<TouchPoint>,
    scroll: Offset,
    scale: Float
): List<SimplePointF> {
    val points = touchPoints.map {
        SimplePointF(
            x = it.x / scale + scroll.x,
            y = (it.y / scale + scroll.y),
        )
    }
    return points
}


/*
* Gets list of points, and return line from first point to last.
* The line consist of 100 points, I do not know how it works (for 20 it want draw correctly)
 */
fun transformToLine(
    startPoint: StrokePoint,
    endPoint: StrokePoint,
): List<StrokePoint> {
    // Helper function to interpolate between two values
    fun lerp(start: Float, end: Float, fraction: Float) = start + (end - start) * fraction

    val numberOfPoints = 100 // Define how many points should line have
    val points2 = List(numberOfPoints) { i ->
        val fraction = i.toFloat() / (numberOfPoints - 1)

        val x = lerp(startPoint.x, endPoint.x, fraction)
        val y = lerp(startPoint.y, endPoint.y, fraction)

        val startPressure = startPoint.pressure
        val endPressure = endPoint.pressure
        val pressure = when {
            startPressure == null && endPressure == null -> null
            startPressure != null && endPressure != null ->
                lerp(startPressure, endPressure, fraction)

            else -> throw IllegalArgumentException(
                "Inconsistent pressure values: " +
                        "startPoint.pressure=${startPoint.pressure}, " +
                        "endPoint.pressure=${endPoint.pressure}. " +
                        "Both must be null or both must be non-null."
            )
        }

        val startTiltX = startPoint.tiltX
        val endTiltX = endPoint.tiltX
        val tiltX = when {
            startTiltX == null && endTiltX == null -> null
            startTiltX != null && endTiltX != null ->
                lerp(startTiltX.toFloat(), endTiltX.toFloat(), fraction).toInt()

            else ->
                throw IllegalArgumentException("startPoint.tiltX and endPoint.tiltX must either both be null or both non-null")
        }

        val startTiltY = startPoint.tiltY
        val endTiltY = endPoint.tiltY
        val tiltY = when {
            startTiltY == null && endTiltY == null -> null
            startTiltY != null && endTiltY != null ->
                lerp(startTiltY.toFloat(), endTiltY.toFloat(), fraction).toInt()

            else ->
                throw IllegalArgumentException("startPoint.tiltY and endPoint.tiltY must either both be null or both non-null")
        }

        StrokePoint(x, y, pressure, tiltX, tiltY)
    }
    return (points2)
}
