package com.genesys.feature.notebook.data

import android.content.ComponentCallbacks2
import android.content.Context
import android.content.res.Configuration
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Bitmap
import android.graphics.Rect
import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.geometry.Offset
import com.genesys.core.domain.repository.notebook.NotebookKeyValueRepository
import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.core.domain.repository.notebook.NotebookStrokeRepository
import com.genesys.core.domain.repository.notebook.NotebookImageRepository
import com.genesys.core.model.notebook.NotebookImage
import com.genesys.core.model.notebook.NotebookKeyValue
import com.genesys.core.model.notebook.NotebookPen
import com.genesys.core.model.notebook.NotebookPenSetting
import com.genesys.core.model.notebook.NotebookPage
import com.genesys.core.model.notebook.NotebookStroke
import com.genesys.feature.notebook.data.model.BackgroundType
import com.genesys.feature.notebook.editor.canvas.CanvasEventBus
import com.genesys.feature.notebook.editor.state.Eraser
import com.genesys.feature.notebook.editor.state.Mode
import com.genesys.feature.notebook.editor.utils.persistBitmapFull
import com.genesys.feature.notebook.editor.utils.persistBitmapThumbnail
import com.genesys.feature.notebook.io.loadBackgroundBitmap
import com.genesys.feature.notebook.ui.SnackConf
import com.genesys.feature.notebook.ui.SnackState
import com.genesys.feature.notebook.ui.showHint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.ref.SoftReference
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max

private const val TAG = "PageDataManager"
private const val EDITOR_SETTINGS_KEY = "EDITOR_SETTINGS"
private const val EDITOR_SETTINGS_VERSION = 2

/**
 * Screen dimensions — filled at runtime from display metrics.
 */
object ScreenDimensions {
    var SCREEN_WIDTH: Int = 1404
    var SCREEN_HEIGHT: Int = 1872

    fun init(width: Int, height: Int) {
        SCREEN_WIDTH = width
        SCREEN_HEIGHT = height
    }
}

/**
 * Cached background for a page.
 */
data class CachedBackground(val path: String, val pageNumber: Int, val scale: Float) {
    val id: String = keyOf(path, pageNumber)
    var bitmap: Bitmap? = loadBackgroundBitmap(path, pageNumber, scale)

    fun matches(filePath: String, pageNum: Int, targetScale: Float): Boolean {
        return path == filePath && pageNumber == pageNum && scale >= targetScale
    }

    companion object {
        fun keyOf(path: String, pageNumber: Int): String {
            val md = MessageDigest.getInstance("SHA-1")
            val bytes = md.digest("$path#$pageNumber".toByteArray(Charsets.UTF_8))
            return bytes.take(8).joinToString("") { "%02x".format(it) }
        }
    }
}

@Singleton
class EditorSettingCacheManager @Inject constructor(
    private val kvRepository: NotebookKeyValueRepository
) {
    data class EditorSettings(
        val version: Int = EDITOR_SETTINGS_VERSION,
        val isToolbarOpen: Boolean,
        val mode: com.genesys.feature.notebook.editor.state.Mode =
            com.genesys.feature.notebook.editor.state.Mode.Draw,
        val pen: com.genesys.core.model.notebook.NotebookPen =
            com.genesys.core.model.notebook.NotebookPen.BALLPEN,
        val eraser: com.genesys.feature.notebook.editor.state.Eraser =
            com.genesys.feature.notebook.editor.state.Eraser.PEN,
        val penSettings: Map<String, com.genesys.core.model.notebook.NotebookPenSetting>
    )

    private val initMutex = Mutex()
    @Volatile
    private var isInitialized = false
    @Volatile
    private var current: EditorSettings? = null

    suspend fun init() {
        if (isInitialized) return
        initMutex.withLock {
            if (isInitialized) return

            val raw = withContext(Dispatchers.IO) {
                kvRepository.get(EDITOR_SETTINGS_KEY)?.value
            }
            val settings = raw?.let(::decodeEditorSettings)
            if (settings?.version == EDITOR_SETTINGS_VERSION) {
                current = settings
            }
            isInitialized = true
        }
    }

    fun setEditorSettings(settings: EditorSettings, shouldPersist: Boolean = true) {
        current = settings
        if (!shouldPersist) return
        val json = runCatching { encodeEditorSettings(settings) }.getOrNull() ?: return
        CoroutineScope(Dispatchers.IO).launch {
            kvRepository.set(NotebookKeyValue(EDITOR_SETTINGS_KEY, json))
        }
    }

    fun getEditorSettings(): EditorSettings? = current

    private fun encodeEditorSettings(settings: EditorSettings): String {
        val penSettingsJson = JSONObject()
        settings.penSettings.forEach { (name, penSetting) ->
            penSettingsJson.put(
                name,
                JSONObject()
                    .put("strokeSize", penSetting.strokeSize.toDouble())
                    .put("color", penSetting.color)
            )
        }

        return JSONObject()
            .put("version", settings.version)
            .put("isToolbarOpen", settings.isToolbarOpen)
            .put("mode", settings.mode.name)
            .put("pen", settings.pen.name)
            .put("eraser", settings.eraser.name)
            .put("penSettings", penSettingsJson)
            .toString()
    }

    private fun decodeEditorSettings(raw: String): EditorSettings? {
        return runCatching {
            val json = JSONObject(raw)
            val penSettingsJson = json.optJSONObject("penSettings") ?: JSONObject()
            val penSettings = buildMap {
                val keys = penSettingsJson.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    val value = penSettingsJson.getJSONObject(key)
                    put(
                        key,
                        NotebookPenSetting(
                            strokeSize = value.getDouble("strokeSize").toFloat(),
                            color = value.getInt("color")
                        )
                    )
                }
            }

            EditorSettings(
                version = json.optInt("version", EDITOR_SETTINGS_VERSION),
                isToolbarOpen = json.optBoolean("isToolbarOpen", false),
                mode = Mode.valueOf(json.optString("mode", Mode.Draw.name)),
                pen = NotebookPen.valueOf(json.optString("pen", NotebookPen.BALLPEN.name)),
                eraser = Eraser.valueOf(json.optString("eraser", Eraser.PEN.name)),
                penSettings = penSettings
            )
        }.getOrNull()
    }
}

/**
 * Adapted from Notable's PageDataManager, using oReader's domain repositories.
 * This is a singleton cache manager that loads page data from the database,
 * caches strokes/images/backgrounds, and manages memory.
 */
@Singleton
class PageDataManager @Inject constructor(
    private val pageRepository: NotebookPageRepository,
    private val strokeRepository: NotebookStrokeRepository,
    private val imageRepository: NotebookImageRepository,
    private val notebookRepository: NotebookRepository,
) {
    private val dataScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    var pageFromDb: NotebookPage? = null

    private val strokes = LinkedHashMap<String, MutableList<NotebookStroke>>()
    private var strokesById = LinkedHashMap<String, HashMap<String, NotebookStroke>>()

    private val images = LinkedHashMap<String, MutableList<NotebookImage>>()
    private var imagesById = LinkedHashMap<String, HashMap<String, NotebookImage>>()

    private val backgroundCache = LinkedHashMap<String, CachedBackground>()
    private val pageToBackgroundKey = HashMap<String, String>()
    private val bitmapCache = LinkedHashMap<String, SoftReference<Bitmap>>()

    private var pageHigh = mutableStateMapOf<String, Int>()
    private var pageScroll = mutableStateMapOf<String, Offset>()
    private var pageZoom = LinkedHashMap<String, Float>()

    @Volatile
    private var currentPage = ""
    @Volatile
    private var currentPageNumber = -1

    fun getCurrentPageId(): String = currentPage

    private val accessLock = Any()
    private var entrySizeMB = LinkedHashMap<String, Int>()

    private val jobLock = Mutex()
    private val dataLoadingJobs = mutableMapOf<String, Job>()
    val dataLoadingScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val saveTopic = MutableSharedFlow<String>()
    @Volatile
    private var componentCallbacksRegistered = false

    private suspend fun waitForPageLoad(pageId: String) {
        val job = jobLock.withLock { dataLoadingJobs[pageId] }
        if (job == null || job.isCancelled) {
            Log.e(TAG, "Illegal state: Job missing or cancelled for $pageId.")
            showHint("Illegal state: Job: $job.")
            return
        }
        job.join()
        if (!validatePageDataLoaded(pageId))
            Log.e(TAG, "Illegal state: after loading page, it is still not loaded correctly")
    }

    private suspend fun getOrStartLoadingJob(pageId: String, bookId: String?): Job {
        return jobLock.withLock {
            val existing = dataLoadingJobs[pageId]
            when {
                existing?.isActive == true -> existing
                existing?.isCompleted == true -> existing
                existing == null || existing.isCancelled -> {
                    val newJob = dataLoadingScope.launch { loadPageFromDb(this, pageId) }
                    dataLoadingJobs[pageId] = newJob
                    newJob
                }
                else -> error("Unexpected job state for Page($pageId)")
            }
        }
    }

    suspend fun requestCurrentPageLoadJoin() {
        val bookId = pageFromDb?.notebookId
        getOrStartLoadingJob(currentPage, bookId).join()
    }

    fun requestPageLoad(pageId: String) {
        dataLoadingScope.launch { getOrStartLoadingJob(pageId, null) }
    }

    private suspend fun loadPageFromDb(scope: CoroutineScope, pageId: String) {
        try {
            val pageWithStrokes = pageRepository.getWithStrokesById(pageId)
            cacheStrokes(pageId, pageWithStrokes.strokes)
            val pageWithImages = pageRepository.getWithImagesById(pageId)
            cacheImages(pageId, pageWithImages.images)
            recomputeHeight(pageId)
            indexImages(scope, pageId)
            indexStrokes(scope, pageId)
            calculateMemoryUsage(pageId, 1)
        } catch (e: Exception) {
            Log.e(TAG, "Error loading page $pageId", e)
        }
    }

    fun validatePageDataLoaded(pageId: String): Boolean {
        val jobSnapshot: Job? = if (jobLock.tryLock()) {
            try { dataLoadingJobs[pageId] } finally { jobLock.unlock() }
        } else null

        if (jobSnapshot?.isActive == true) return false
        val jobDone = jobSnapshot?.isCompleted ?: false
        val dataLoaded = areListInitialized(pageId)

        if (jobSnapshot != null && dataLoaded != jobDone) {
            SnackState.logAndShowError(
                TAG, "Inconsistent state for page($pageId): dataLoaded=$dataLoaded, jobDone=$jobDone"
            )
            dataLoadingScope.launch {
                jobLock.withLock { dataLoadingJobs.remove(pageId)?.cancel() }
                removePage(pageId)
            }
            return false
        }
        return dataLoaded
    }

    private fun areListInitialized(pageId: String): Boolean {
        return synchronized(accessLock) {
            strokes.containsKey(pageId) && images.containsKey(pageId) && entrySizeMB.containsKey(pageId)
        }
    }

    fun collectAndPersistBitmapsBatch(context: Context, scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            saveTopic.collect { pageId ->
                val ref = bitmapCache[pageId]
                val currentZoomLevel = pageZoom[pageId]
                val currentScroll = pageScroll[pageId]
                val bitmap = ref?.get()

                if (bitmap == null || bitmap.isRecycled) {
                    Log.e(TAG, "Page $pageId: Bitmap is null/recycled — cannot persist")
                    return@collect
                }

                scope.launch(Dispatchers.IO) {
                    persistBitmapFull(context, bitmap, pageId, currentScroll, currentZoomLevel)
                    persistBitmapThumbnail(context, bitmap, pageId)
                }
            }
        }
    }

    suspend fun setPage(pageId: String) {
        pageFromDb = pageRepository.getById(pageId)
        pageFromDb?.notebookId?.let { notebookId ->
            currentPageNumber = notebookRepository.getPageIndex(notebookId, pageId) ?: -1
        }
        currentPage = pageId
    }

    suspend fun refreshPageFromDb() {
        pageFromDb = pageRepository.getById(currentPage)
    }

    fun getCachedBitmap(pageId: String): Bitmap? {
        return bitmapCache[pageId]?.get()?.takeIf { !it.isRecycled && it.isMutable }
    }

    fun cacheBitmap(pageId: String, bitmap: Bitmap) {
        bitmapCache[pageId] = SoftReference(bitmap)
    }

    fun getPageHeight(pageId: String): Int? = pageHigh[pageId]
    fun setPageHeight(pageId: String, height: Int) { pageHigh[pageId] = height }

    fun recomputeHeight(pageId: String): Int {
        synchronized(accessLock) {
            if (strokes[pageId].isNullOrEmpty()) return ScreenDimensions.SCREEN_HEIGHT
            val maxStrokeBottom = strokes[pageId]!!.maxOf { it.bottom }.plus(50)
            pageHigh[pageId] = max(maxStrokeBottom.toInt(), ScreenDimensions.SCREEN_HEIGHT)
            return pageHigh[pageId]!!
        }
    }

    fun computeWidth(pageId: String): Int {
        synchronized(accessLock) {
            if (strokes[pageId].isNullOrEmpty()) return ScreenDimensions.SCREEN_WIDTH
            val maxStrokeRight = strokes[pageId]!!.maxOf { it.right }.plus(50)
            return max(maxStrokeRight.toInt(), ScreenDimensions.SCREEN_WIDTH)
        }
    }

    fun getPageScroll(pageId: String): Offset {
        return pageScroll.getOrPut(pageId) {
            Offset(0f, pageFromDb?.scroll?.toFloat() ?: 0f)
        }
    }

    fun setPageScroll(pageId: String, scroll: Offset) { pageScroll[pageId] = scroll }

    fun getPageZoom(pageId: String): Float = pageZoom.getOrPut(pageId) { 1f }
    fun setPageZoom(pageId: String, zoom: Float) { pageZoom[pageId] = zoom }

    fun isTransformationAllowedForCurrentPage(): Boolean {
        return when (pageFromDb?.backgroundType) {
            "native", null -> true
            "coverImage" -> false
            else -> true
        }
    }

    fun getCurrentPageNumber(): Int = currentPageNumber

    fun getStrokes(pageId: String): List<NotebookStroke> = strokes[pageId] ?: emptyList()
    fun setStrokes(pageId: String, data: List<NotebookStroke>) { strokes[pageId] = data.toMutableList() }
    fun getStrokesById(pageId: String): HashMap<String, NotebookStroke> = strokesById[pageId] ?: hashMapOf()

    fun getImages(pageId: String): List<NotebookImage> = images[pageId] ?: emptyList()
    fun setImages(pageId: String, data: List<NotebookImage>) { images[pageId] = data.toMutableList() }

    fun indexStrokes(scope: CoroutineScope, pageId: String) {
        scope.launch {
            strokesById[pageId] = hashMapOf(*strokes[pageId]!!.map { s -> s.id to s }.toTypedArray())
        }
    }

    fun indexImages(scope: CoroutineScope, pageId: String) {
        scope.launch {
            imagesById[pageId] = hashMapOf(*images[pageId]!!.map { img -> img.id to img }.toTypedArray())
        }
    }

    fun getStrokes(strokeIds: List<String>, pageId: String): List<NotebookStroke?> {
        return strokeIds.map { s -> strokesById[pageId]?.get(s) }
    }

    fun getImage(imageId: String, pageId: String): NotebookImage? = imagesById[pageId]?.get(imageId)

    fun getImages(imageIds: List<String>, pageId: String): List<NotebookImage?> {
        return imageIds.map { i -> imagesById[pageId]?.get(i) }
    }

    fun getImagesInRectangle(inPageCoordinates: Rect, id: String): List<NotebookImage>? {
        synchronized(accessLock) {
            if (!validatePageDataLoaded(id)) return null
            val imageList = images[id] ?: return emptyList()
            return imageList.filter { image ->
                image.x < inPageCoordinates.right && (image.x + image.width) > inPageCoordinates.left &&
                        image.y < inPageCoordinates.bottom && (image.y + image.height) > inPageCoordinates.top
            }
        }
    }

    fun getStrokesInRectangle(inPageCoordinates: Rect, id: String): List<NotebookStroke>? {
        synchronized(accessLock) {
            if (!validatePageDataLoaded(id)) return null
            val strokeList = strokes[id] ?: return emptyList()
            return strokeList.filter { stroke ->
                stroke.right > inPageCoordinates.left && stroke.left < inPageCoordinates.right &&
                        stroke.bottom > inPageCoordinates.top && stroke.top < inPageCoordinates.bottom
            }
        }
    }

    fun updateStrokesInDb(data: List<NotebookStroke>) {
        dataScope.launch { strokeRepository.update(data) }
    }

    fun saveStrokesToDb(data: List<NotebookStroke>) {
        dataScope.launch {
            try {
                strokeRepository.create(data)
            } catch (_: SQLiteConstraintException) {
                SnackState.logAndShowError(TAG, "Attempted to create strokes that already exist")
                strokeRepository.update(data)
            }
        }
    }

    fun saveImagesToDb(data: List<NotebookImage>) {
        dataScope.launch { imageRepository.create(data) }
    }

    fun removeStrokesFromDb(ids: List<String>) {
        dataScope.launch { strokeRepository.deleteAll(ids) }
    }

    fun removeImagesFromDb(ids: List<String>) {
        dataScope.launch { imageRepository.deleteAll(ids) }
    }

    fun setScrollInDb() {
        dataScope.launch {
            pageRepository.updateScroll(currentPage, getPageScroll(currentPage).y.toInt())
        }
    }

    fun getBackgroundType(): BackgroundType? {
        val type = pageFromDb?.backgroundType ?: return null
        return BackgroundType.fromKey(type)
    }

    fun getBackgroundName(): String = pageFromDb?.background ?: "blank"

    private fun cacheStrokes(pageId: String, data: List<NotebookStroke>) {
        synchronized(accessLock) {
            if (!strokes.containsKey(pageId)) {
                strokes[pageId] = data.toMutableList()
            } else {
                strokes[pageId]?.addAll(data)
            }
        }
    }

    private fun cacheImages(pageId: String, data: List<NotebookImage>) {
        synchronized(accessLock) {
            if (!images.containsKey(pageId)) {
                images[pageId] = data.toMutableList()
            } else {
                images[pageId]?.addAll(data)
            }
        }
    }

    fun setCurrentBackground(background: CachedBackground) {
        setBackground(currentPage, background)
    }

    fun setBackground(pageId: String, background: CachedBackground) {
        synchronized(accessLock) {
            val existing = backgroundCache[background.id]
            if (existing == null || background.scale > existing.scale) {
                backgroundCache[background.id] = background
            }
            pageToBackgroundKey[pageId] = background.id
        }
    }

    fun getCurrentBackground(): CachedBackground {
        return synchronized(accessLock) {
            val key = pageToBackgroundKey[currentPage]
            val bg = if (key != null) backgroundCache[key] else null
            bg ?: CachedBackground("", 0, 1.0f)
        }
    }

    suspend fun getPageNumberInCurrentNotebook(pageId: String): Int {
        val notebookId = pageFromDb?.notebookId ?: return -1
        return notebookRepository.getPageIndex(notebookId, pageId) ?: -1
    }

    fun updateOnExit(targetPageId: String) {
        if (validatePageDataLoaded(targetPageId)) {
            recomputeHeight(targetPageId)
            calculateMemoryUsage(targetPageId, 0)
        }
    }

    // --- Memory management ---

    @Volatile
    private var currentCacheSizeMB = 0

    fun removePage(pageId: String): Boolean {
        if (pageId == currentPage) {
            SnackState.logAndShowError(TAG, "Cannot remove current page")
            return false
        }
        synchronized(accessLock) {
            strokes.remove(pageId)
            images.remove(pageId)
            pageHigh.remove(pageId)
            pageZoom.remove(pageId)
            pageScroll.remove(pageId)
            bitmapCache.remove(pageId)
            strokesById.remove(pageId)
            imagesById.remove(pageId)
            dataLoadingJobs.remove(pageId)
            currentCacheSizeMB -= entrySizeMB[pageId] ?: 0
            entrySizeMB.remove(pageId)

            val key = pageToBackgroundKey.remove(pageId)
            if (key != null && !pageToBackgroundKey.values.any { it == key }) {
                backgroundCache.remove(key)
            }
        }
        return true
    }

    fun clearAllPages() {
        dataLoadingScope.launch {
            jobLock.withLock {
                dataLoadingJobs.forEach { (id, _) -> removePage(id) }
            }
        }
    }

    fun hasEnoughMemory(requiredMb: Int): Boolean {
        val availableMem = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()
        return availableMem > requiredMb * 1024 * 1024L
    }

    fun reduceCache(cacheSizeLimitMb: Int) {
        freeMemory(cacheSizeLimitMb)
    }

    fun cacheNeighbors() {
        // Deferred optimization from the original implementation.
    }

    fun cancelLoadingPage(pageId: String) {
        dataLoadingJobs[pageId]?.cancel()
    }

    private fun calculateMemoryUsage(pageId: String, sign: Int = 1): Int {
        return synchronized(accessLock) {
            var totalBytes = 0L
            strokes[pageId]?.let { strokeList ->
                totalBytes += strokeList.sumOf { stroke ->
                    var mem = 120L
                    mem += stroke.points.size * 32L
                    mem += 16L
                    mem
                }
            }
            totalBytes += images.size * 100L
            backgroundCache[pageToBackgroundKey[pageId]]?.bitmap?.let { bitmap ->
                totalBytes += bitmap.allocationByteCount.toLong()
            }
            bitmapCache[pageId]?.get()?.let { bitmap ->
                if (!bitmap.isRecycled) totalBytes += bitmap.allocationByteCount.toLong()
            }
            totalBytes += 40L * 4
            val memoryUsedMB = (totalBytes / (1024 * 1024)).toInt()
            entrySizeMB[pageId] = memoryUsedMB
            currentCacheSizeMB += memoryUsedMB * sign
            memoryUsedMB
        }
    }

    private fun freeMemory(cacheSizeLimit: Int): Boolean {
        synchronized(accessLock) {
            val pagesToRemove = strokes.keys.filter { it != currentPage }
            for (pageId in pagesToRemove) {
                if (currentCacheSizeMB <= cacheSizeLimit) break
                if (!removePage(pageId)) break
            }
            currentCacheSizeMB = maxOf(0, currentCacheSizeMB)
            return currentCacheSizeMB <= cacheSizeLimit
        }
    }

    fun registerComponentCallbacks(context: Context) {
        if (componentCallbacksRegistered) return
        synchronized(this) {
            if (componentCallbacksRegistered) return
            componentCallbacksRegistered = true
        }
        context.registerComponentCallbacks(object : ComponentCallbacks2 {
            @Suppress("DEPRECATION")
            override fun onTrimMemory(level: Int) {
                when (level) {
                    ComponentCallbacks2.TRIM_MEMORY_COMPLETE -> freeMemory(0)
                    ComponentCallbacks2.TRIM_MEMORY_MODERATE -> freeMemory(32)
                    ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL -> freeMemory(64)
                    ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW -> freeMemory(128)
                    ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE -> freeMemory(256)
                    ComponentCallbacks2.TRIM_MEMORY_BACKGROUND -> freeMemory(32)
                    ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN -> freeMemory(10)
                }
            }

            override fun onConfigurationChanged(newConfig: Configuration) {}

            @Deprecated("Deprecated in Java")
            override fun onLowMemory() { freeMemory(0) }
        })
    }
}
