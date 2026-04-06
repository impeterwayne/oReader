package com.genesys.feature.notebook.editor.drawing

import android.annotation.SuppressLint
import android.graphics.Color
import android.opengl.GLES20
import android.opengl.Matrix
import android.os.Build
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import androidx.annotation.WorkerThread
import androidx.core.graphics.toColor
import androidx.graphics.lowlatency.BufferInfo
import androidx.graphics.lowlatency.GLFrontBufferedRenderer
import androidx.graphics.opengl.egl.EGLManager
import androidx.input.motionprediction.MotionEventPredictor
import com.genesys.feature.notebook.editor.canvas.DrawCanvas
import android.util.Log

/**
 * Front-buffered stylus renderer ported from Notable.
 *
 * The previous compatibility stub only consumed touch events and never rendered
 * anything, which removed the original fast stylus preview path entirely.
 */
class OpenGLRenderer(
    private var viewModel: DrawCanvas
) : GLFrontBufferedRenderer.Callback<StrokePoint> {
    private val mvpMatrix = FloatArray(16)
    private val projection = FloatArray(16)

    var frontBufferRenderer: GLFrontBufferedRenderer<StrokePoint>? = null
        private set
    private var motionEventPredictor: MotionEventPredictor? = null

    private var lineRenderer: LineRenderer = LineRenderer()

    @WorkerThread
    private fun obtainRenderer(): LineRenderer =
        if (lineRenderer.isInitialized) {
            lineRenderer
        } else {
            lineRenderer.apply { initialize() }
        }

    val openGlPoints2 = mutableListOf<StrokePoint>()

    fun clearPointBuffer() {
        openGlPoints2.clear()
    }

    private var timeOfLastRefreshMs = 0L

    override fun onDrawFrontBufferedLayer(
        eglManager: EGLManager,
        width: Int,
        height: Int,
        bufferInfo: BufferInfo,
        transform: FloatArray,
        param: StrokePoint
    ) {
        val bufferWidth = bufferInfo.width
        val bufferHeight = bufferInfo.height
        GLES20.glViewport(0, 0, bufferWidth, bufferHeight)
        Matrix.orthoM(
            mvpMatrix,
            0,
            0f,
            bufferWidth.toFloat(),
            0f,
            bufferHeight.toFloat(),
            -1f,
            1f
        )
        Matrix.multiplyMM(projection, 0, mvpMatrix, 0, transform, 0)

        openGlPoints2.add(param)
        if (openGlPoints2.size < 2) return
        if (System.currentTimeMillis() - timeOfLastRefreshMs < 16) return

        val pointsToDraw = openGlPoints2.toList()
        openGlPoints2.clear()
        openGlPoints2.add(pointsToDraw.last())
        timeOfLastRefreshMs = System.currentTimeMillis()

        obtainRenderer().drawLine(projection, pointsToDraw, Color.BLACK.toColor(), viewModel)
    }

    override fun onDrawMultiBufferedLayer(
        eglManager: EGLManager,
        width: Int,
        height: Int,
        bufferInfo: BufferInfo,
        transform: FloatArray,
        params: Collection<StrokePoint>
    ) {
        val bufferWidth = bufferInfo.width
        val bufferHeight = bufferInfo.height
        GLES20.glViewport(0, 0, bufferWidth, bufferHeight)
        Matrix.orthoM(
            mvpMatrix,
            0,
            0f,
            bufferWidth.toFloat(),
            0f,
            bufferHeight.toFloat(),
            -1f,
            1f
        )
        Matrix.multiplyMM(projection, 0, mvpMatrix, 0, transform, 0)

        if (openGlPoints2.size < 2) return
        if (System.currentTimeMillis() - timeOfLastRefreshMs < 16) return

        val pointsToDraw = openGlPoints2.toList()
        openGlPoints2.clear()
        openGlPoints2.add(pointsToDraw.last())
        timeOfLastRefreshMs = System.currentTimeMillis()

        obtainRenderer().drawSimpleLine(projection, pointsToDraw, Color.RED.toColor(), viewModel)
    }

    fun attachSurfaceView(surfaceView: SurfaceView) {
        if (isAttached) {
            Log.w("OpenGLRenderer", "Already attached, releasing old renderer first")
            release()
        }
        frontBufferRenderer = GLFrontBufferedRenderer(surfaceView, this)
        motionEventPredictor = MotionEventPredictor.newInstance(surfaceView)
    }

    val isAttached: Boolean
        get() = frontBufferRenderer != null

    fun release() {
        Log.d("OpenGLRenderer", "Releasing renderer")
        frontBufferRenderer?.release(true) {
            obtainRenderer().release()
        }
        frontBufferRenderer = null
        motionEventPredictor = null
    }

    private fun getStrokePoint(motionEvent: MotionEvent): StrokePoint =
        StrokePoint(
            x = motionEvent.x,
            y = motionEvent.y
        )

    @SuppressLint("ClickableViewAccessibility")
    val onTouchListener = View.OnTouchListener { view, event ->
        val point = getStrokePoint(event)
        motionEventPredictor?.record(event)

        if (event.getToolType(0) != MotionEvent.TOOL_TYPE_STYLUS) {
            return@OnTouchListener true
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                openGlPoints2.clear()
                view.requestUnbufferedDispatch(event)
                frontBufferRenderer?.renderFrontBufferedLayer(point)
            }

            MotionEvent.ACTION_MOVE -> {
                frontBufferRenderer?.renderFrontBufferedLayer(point)
            }

            MotionEvent.ACTION_UP -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                    (event.flags and MotionEvent.FLAG_CANCELED) == MotionEvent.FLAG_CANCELED
                ) {
                    frontBufferRenderer?.cancel()
                } else {
                    frontBufferRenderer?.commit()
                }
            }

            MotionEvent.ACTION_CANCEL -> {
                frontBufferRenderer?.cancel()
            }
        }

        true
    }
}
