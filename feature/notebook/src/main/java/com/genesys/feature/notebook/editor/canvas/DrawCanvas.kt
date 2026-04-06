package com.genesys.feature.notebook.editor.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
// TODO: Replace with oReader repositories
import com.genesys.feature.notebook.editor.model.SimplePointF
import com.genesys.feature.notebook.editor.state.Mode
import com.genesys.feature.notebook.editor.PageView
import com.genesys.feature.notebook.editor.drawing.OpenGLRenderer
import com.genesys.feature.notebook.editor.drawing.selectPaint
import com.genesys.feature.notebook.editor.state.EditorState
import com.genesys.feature.notebook.editor.state.History
import com.genesys.feature.notebook.editor.state.Operation
import com.genesys.feature.notebook.editor.utils.DeviceCompat
import com.genesys.feature.notebook.editor.utils.onSurfaceChanged
import com.genesys.feature.notebook.editor.utils.onSurfaceDestroy
import com.genesys.feature.notebook.editor.utils.pointsToPath
import com.genesys.feature.notebook.editor.utils.refreshScreenRegion
import com.onyx.android.sdk.api.device.epd.EpdController
import android.util.Log
import kotlinx.coroutines.CoroutineScope


val pressure = EpdController.getMaxTouchPressure()

// keep reference of the surface view presently associated to the singleton touchhelper
var referencedSurfaceView: String = ""

@SuppressLint("ViewConstructor") // we never execute constructor from XML
class DrawCanvas(
    context: Context,
    val coroutineScope: CoroutineScope,
    val state: EditorState,
    val page: PageView,
    val history: History
) : SurfaceView(context) {
    private val TAG = "DrawCanvas"

    override fun onTouchEvent(event: MotionEvent): Boolean { //Custom view DrawCanvas overrides onTouchEvent but not performClick
        if (event.action == MotionEvent.ACTION_UP) {
            performClick()
        }
        // We will only capture stylus events, and past rest down
//        log.d("onTouchEvent, ${event.getToolType(0)}")
        if (event.getToolType(0) == MotionEvent.TOOL_TYPE_STYLUS || event.getToolType(0) == MotionEvent.TOOL_TYPE_ERASER) {
            return if (!DeviceCompat.isOnyxDevice || inputHandler.isErasing) glRenderer.onTouchListener.onTouch(
                this, event
            )
            else true
        }
        // Pass everything else down
        return super.onTouchEvent(event)
    }

    @Suppress("RedundantOverride")
    override fun performClick(): Boolean {
        return super.performClick()
    }
    var glRenderer = OpenGLRenderer(this)

    fun getActualState(): EditorState {
        return this.state
    }

    private val strokeHistoryBatch = mutableListOf<String>()
    internal fun commitToHistory() {
        if (strokeHistoryBatch.isNotEmpty()) history.addOperationsToHistory(
            operations = listOf(
                Operation.DeleteStroke(strokeHistoryBatch.map { it })
            )
        )
        strokeHistoryBatch.clear()
        //testing if it will help with undo hiding strokes.
        drawCanvasToView(null)
    }


    val inputHandler =
        OnyxInputHandler(this, page, state, history, coroutineScope, strokeHistoryBatch)
    val refreshManager = CanvasRefreshManager(this, page, state, inputHandler.touchHelper)


    private val observers = CanvasObserverRegistry(
        coroutineScope, this, page, state, history, inputHandler, refreshManager
    )

    fun registerObservers() = observers.registerAll()

    fun init() {
        Log.i(TAG, "Initializing Canvas")
        glRenderer = OpenGLRenderer(this@DrawCanvas)
        glRenderer.attachSurfaceView(this)


        val surfaceCallback: SurfaceHolder.Callback = object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                Log.i(TAG, "surface created $holder")
                // set up the drawing surface
                inputHandler.updateActiveSurface()
            }

            override fun surfaceChanged(
                holder: SurfaceHolder, format: Int, width: Int, height: Int
            ) {
                // Only act if actual dimensions changed
                if (page.viewWidth == width && page.viewHeight == height) return

                Log.v(TAG, "Surface dimension changed!")

                // Update page dimensions, redraw and refresh
                page.updateDimensions(width, height)
                inputHandler.updateActiveSurface()
                onSurfaceChanged(this@DrawCanvas)
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                Log.i(TAG,
                    "surface destroyed ${
                        this@DrawCanvas.hashCode()
                    } - ref $referencedSurfaceView"
                )
                holder.removeCallback(this)
                if (referencedSurfaceView == this@DrawCanvas.hashCode().toString()) {
                    inputHandler.touchHelper?.closeRawDrawing()
                }
                onSurfaceDestroy(this@DrawCanvas, inputHandler.touchHelper)
            }
        }

        this.holder.addCallback(surfaceCallback)

    }



    fun drawCanvasToView(dirtyRect: Rect?) {
        val zoneToRedraw = dirtyRect ?: Rect(0, 0, page.viewWidth, page.viewHeight)
        var canvas: Canvas? = null
        try {
            // Lock the canvas only for the dirtyRect region
            canvas = this.holder.lockCanvas(zoneToRedraw) ?: return

            canvas.drawBitmap(page.windowedBitmap, zoneToRedraw, zoneToRedraw, Paint())

            if (getActualState().mode == Mode.Select) {
                // render selection, but only within dirtyRect
                getActualState().selectionState.firstPageCut?.let { cutPoints ->
                    Log.i(TAG, "render cut")
                    val path = pointsToPath(cutPoints.map {
                        SimplePointF(
                            it.x - page.scroll.x, it.y - page.scroll.y
                        )
                    })
                    canvas.drawPath(path, selectPaint)
                }
            }
        } catch (e: IllegalStateException) {
            Log.w(TAG, "Surface released during draw", e)
            // ignore — surface is gone
        } finally {
            try {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas)
                }
            } catch (e: IllegalStateException) {
                Log.w(TAG, "Surface released during unlock", e)
            }
        }
    }


    fun restoreCanvas(dirtyRect: Rect, bitmap: Bitmap = page.windowedBitmap) {
        post {
            val holder = this@DrawCanvas.holder
            var surfaceCanvas: Canvas? = null
            try {
                surfaceCanvas = holder.lockCanvas(dirtyRect)
                // Draw the preview bitmap scaled to fit the dirty rect
                surfaceCanvas.drawBitmap(bitmap, dirtyRect, dirtyRect, null)
            } catch (e: Exception) {
                Log.e("DrawCanvas", "Canvas lock failed: ${e.message}")
            } finally {
                if (surfaceCanvas != null) {
                    holder.unlockCanvasAndPost(surfaceCanvas)
                }
                // Trigger partial refresh
                refreshScreenRegion(this@DrawCanvas, dirtyRect)
            }
        }
    }

}
