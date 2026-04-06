package com.genesys.feature.notebook.editor

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.core.model.notebook.NotebookPen
import com.genesys.core.model.notebook.NotebookPenSetting
import com.genesys.feature.notebook.data.PageDataManager
import com.genesys.feature.notebook.data.EditorSettingCacheManager
import com.genesys.feature.notebook.data.copyImageToDatabase
import com.genesys.feature.notebook.data.model.BackgroundType
import com.genesys.feature.notebook.editor.EditorViewModel.Companion.DEFAULT_PEN_SETTINGS
import com.genesys.feature.notebook.editor.canvas.CanvasEventBus
import com.genesys.feature.notebook.editor.state.Eraser
import com.genesys.feature.notebook.editor.state.Mode
import com.genesys.feature.notebook.editor.state.SelectionState
import com.genesys.feature.notebook.io.ExportFormat
import com.genesys.feature.notebook.io.ExportTarget
import com.genesys.feature.notebook.ui.SnackConf
import com.genesys.feature.notebook.ui.SnackState
import com.genesys.feature.notebook.ui.SnackState.Companion.logAndShowError
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

private const val TAG = "EditorViewModel"

// --------------------------------------------------------
// 1. UI STATE
// --------------------------------------------------------

/**
 * Flat toolbar/editor UI state exposed to Compose.
 * Also used as `EditorUiState` via typealias for backward compatibility.
 */
data class ToolbarUiState(
    // Document info
    val notebookId: String? = null,
    val pageId: String? = null,
    val isBookActive: Boolean = false,
    val pageNumberInfo: String = "1/1",
    val currentPageNumber: Int = 0,

    // Background
    val backgroundType: String = "native",
    val backgroundPath: String = "blank",
    val backgroundPageNumber: Int = 0,

    // Toolbar visibility & menus
    val isToolbarOpen: Boolean = false,
    val isMenuOpen: Boolean = false,
    val isStrokeSelectionOpen: Boolean = false,
    val isBackgroundSelectorModalOpen: Boolean = false,
    val showResetView: Boolean = false,

    // Canvas / drawing
    val mode: Mode = Mode.Draw,
    val pen: NotebookPen = NotebookPen.BALLPEN,
    val eraser: Eraser = Eraser.PEN,
    // TODO: if it is an  emptyMap(), the DrawCanvas crashes, to be fixed.
    val penSettings: Map<String, NotebookPenSetting> = DEFAULT_PEN_SETTINGS,
    val isSelectionActive: Boolean = false,
    val hasClipboard: Boolean = false,
    val isDrawing: Boolean = true,
    val isQuickNavOpen: Boolean = false,
) {
    val isDrawingAllowed: Boolean
        get() = !isSelectionActive &&
                !(isMenuOpen || isStrokeSelectionOpen || isBackgroundSelectorModalOpen)
                && !isQuickNavOpen
}


// --------------------------------------------------------
// 2. USER ACTIONS (Intents)
// --------------------------------------------------------

sealed class ToolbarAction {
    object ToggleToolbar : ToolbarAction()
    data class ChangeMode(val mode: Mode) : ToolbarAction()
    data class ChangePen(val pen: NotebookPen) : ToolbarAction()
    data class ChangePenSetting(val pen: NotebookPen, val setting: NotebookPenSetting) : ToolbarAction()
    data class ChangeEraser(val eraser: Eraser) : ToolbarAction()
    object ToggleMenu : ToolbarAction()
    data class UpdateMenuOpenTo(val isOpen: Boolean) : ToolbarAction()
    data class ToggleBackgroundSelector(val isOpen: Boolean) : ToolbarAction()
    data class ToggleScribbleToErase(val enabled: Boolean) : ToolbarAction()

    object Undo : ToolbarAction()
    object Redo : ToolbarAction()
    object Paste : ToolbarAction()
    object ResetView : ToolbarAction()
    object ClearAllStrokes : ToolbarAction()

    data class ImagePicked(val uri: Uri) : ToolbarAction()
    data class ExportPage(val format: ExportFormat) : ToolbarAction()
    data class ExportBook(val format: ExportFormat) : ToolbarAction()
    data class BackgroundChanged(val type: String, val path: String?) : ToolbarAction()

    object NavigateToLibrary : ToolbarAction()
    object NavigateToBugReport : ToolbarAction()
    object NavigateToPages : ToolbarAction()
    object NavigateToHome : ToolbarAction()

    object CloseAllMenus : ToolbarAction()
    data class UpdateQuickNavOpen(val isOpen: Boolean) : ToolbarAction()
}


// --------------------------------------------------------
// 3. CANVAS COMMANDS (Imperative drawing actions)
// --------------------------------------------------------

sealed class CanvasCommand {
    object Undo : CanvasCommand()
    object Redo : CanvasCommand()
    object Paste : CanvasCommand()
    object ResetView : CanvasCommand()
    object ClearAllStrokes : CanvasCommand()
    object RefreshCanvas : CanvasCommand()
    data class CopyImageToCanvas(val uri: Uri) : CanvasCommand()
}

// --------------------------------------------------------
// 4. UI EVENTS (Navigation, Snackbars)
// --------------------------------------------------------

sealed class EditorUiEvent {
    data class NavigateToLibrary(val folderId: String?) : EditorUiEvent()
    data class NavigateToPages(val bookId: String) : EditorUiEvent()
    object NavigateToBugReport : EditorUiEvent()
}

// --------------------------------------------------------
// 5. VIEW MODEL
// --------------------------------------------------------

@HiltViewModel
class EditorViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val pageRepository: NotebookPageRepository,
    private val notebookRepository: NotebookRepository,
    var editorSettingCacheManager: EditorSettingCacheManager,
    val pageDataManager: PageDataManager
) : ViewModel() {

    // ---- Toolbar / UI State (single flat flow) ----
    private val _toolbarState = MutableStateFlow(ToolbarUiState())
    val toolbarState: StateFlow<ToolbarUiState> = _toolbarState.asStateFlow()

    // ---- One-Time Events (Channels) ----
    private val uiEventChannel = Channel<EditorUiEvent>(Channel.BUFFERED)
    val uiEvents = uiEventChannel.receiveAsFlow()

    private val canvasCommandChannel = Channel<CanvasCommand>(Channel.BUFFERED)
    val canvasCommands = canvasCommandChannel.receiveAsFlow()

    // ---- Internal document context ----
    private var bookId: String? = null
    private val currentPageId: String get() = _toolbarState.value.pageId.orEmpty()

    // ---- Init guard ----
    private val didInitSettings = AtomicBoolean(false)

    // ---- Selection state (kept for drawing logic compatibility) ----
    val selectionState = SelectionState()

    // --------------------------------------------------------
    // Initialization from persisted settings
    // --------------------------------------------------------

    /**
     * Restores editor settings from the persisted cache.
     * Idempotent: only applies settings on first call; subsequent calls are no-ops.
     */
    fun initFromPersistedSettings() {
        if (!didInitSettings.compareAndSet(false, true)) return
        val settings = editorSettingCacheManager.getEditorSettings()
        _toolbarState.update {
            it.copy(
                mode = settings?.mode ?: Mode.Draw,
                pen = settings?.pen ?: NotebookPen.BALLPEN,
                eraser = settings?.eraser ?: Eraser.PEN,
                isToolbarOpen = settings?.isToolbarOpen ?: false,
                penSettings = settings?.penSettings?.takeIf { it.isNotEmpty() } ?: DEFAULT_PEN_SETTINGS
            )
        }
    }

    fun onDispose(page: PageView) {
        // finish selection operation
        selectionState.applySelectionDisplace(page)
        // TODO: export to linked file on dispose
        page.disposeOldPage()
    }

    // --------------------------------------------------------
    // Toolbar Action Dispatch
    // --------------------------------------------------------

    fun onToolbarAction(action: ToolbarAction) {
        Log.v(TAG, "onToolbarAction: $action")
        when (action) {
            is ToolbarAction.ToggleToolbar -> {
                _toolbarState.update { it.copy(isToolbarOpen = !it.isToolbarOpen) }
                updateDrawingState()
                saveToolbarState()
            }

            is ToolbarAction.ChangeMode -> {
                _toolbarState.update { it.copy(mode = action.mode) }
                updateDrawingState()
                saveToolbarState()
            }

            is ToolbarAction.ChangePen -> handlePenChange(action.pen)
            is ToolbarAction.ChangePenSetting -> handlePenSettingChange(action.pen, action.setting)
            is ToolbarAction.ChangeEraser -> handleEraserChange(action.eraser)
            is ToolbarAction.ToggleMenu -> {
                _toolbarState.update { it.copy(isMenuOpen = !it.isMenuOpen) }
                updateDrawingState()
            }

            is ToolbarAction.UpdateMenuOpenTo -> {
                _toolbarState.update { it.copy(isStrokeSelectionOpen = action.isOpen) }
                updateDrawingState()
            }

            is ToolbarAction.ToggleBackgroundSelector -> {
                _toolbarState.update { it.copy(isBackgroundSelectorModalOpen = action.isOpen) }
                updateDrawingState()
            }

            is ToolbarAction.ToggleScribbleToErase -> updateScribbleToErase(action.enabled)
            is ToolbarAction.ImagePicked -> handleImagePicked(action.uri)
            is ToolbarAction.ExportPage -> handleExport(
                ExportTarget.FILE,
                action.format
            )

            is ToolbarAction.ExportBook -> {
                bookId?.let { handleExport(ExportTarget.SHARE, action.format) }
            }

            is ToolbarAction.BackgroundChanged -> handleBackgroundChange(action.type, action.path)

            ToolbarAction.Undo -> sendCanvasCommand(CanvasCommand.Undo)
            ToolbarAction.Redo -> sendCanvasCommand(CanvasCommand.Redo)
            ToolbarAction.Paste -> sendCanvasCommand(CanvasCommand.Paste)
            ToolbarAction.ResetView -> sendCanvasCommand(CanvasCommand.ResetView)
            ToolbarAction.ClearAllStrokes -> sendCanvasCommand(CanvasCommand.ClearAllStrokes)

            ToolbarAction.NavigateToLibrary -> handleNavigateToLibrary()
            ToolbarAction.NavigateToBugReport -> sendUiEvent(EditorUiEvent.NavigateToBugReport)
            ToolbarAction.NavigateToPages -> handleNavigateToPages()
            ToolbarAction.NavigateToHome -> sendUiEvent(EditorUiEvent.NavigateToLibrary(null))

            ToolbarAction.CloseAllMenus -> handleCloseAllMenus()
            is ToolbarAction.UpdateQuickNavOpen -> {
                _toolbarState.update { it.copy(isQuickNavOpen = action.isOpen) }
                updateDrawingState()
            }
        }
    }

    // --------------------------------------------------------
    // Toolbar Action Handlers (private)
    // --------------------------------------------------------

    private fun handlePenChange(pen: NotebookPen) {
        val state = _toolbarState.value
        if (state.mode == Mode.Draw && state.pen == pen) {
            _toolbarState.update { it.copy(isStrokeSelectionOpen = true) }
        } else {
            _toolbarState.update {
                it.copy(pen = pen, mode = Mode.Draw)
            }
            saveToolbarState()
        }
        updateDrawingState()
    }

    private fun handleEraserChange(eraser: Eraser) {
        _toolbarState.update { it.copy(eraser = eraser) }
        updateDrawingState()
        saveToolbarState()
    }

    private fun handlePenSettingChange(pen: NotebookPen, setting: NotebookPenSetting) {
        val newSettings = _toolbarState.value.penSettings.toMutableMap()
        newSettings[pen.penName] = setting
        _toolbarState.update { it.copy(penSettings = newSettings) }
        saveToolbarState()
    }

    private fun handleCloseAllMenus() {
        Log.d(TAG, "Closing all menus in EditorViewModel")
        _toolbarState.update {
            it.copy(
                isMenuOpen = false,
                isStrokeSelectionOpen = false,
                isBackgroundSelectorModalOpen = false
            )
        }
        updateDrawingState()
    }

    private fun updateScribbleToErase(enabled: Boolean) {
        // TODO: persist scribble-to-erase setting via oReader settings
        Log.d(TAG, "updateScribbleToErase: $enabled")
    }

    private fun handleImagePicked(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val copiedFile = copyImageToDatabase(context, uri)
                sendCanvasCommand(CanvasCommand.CopyImageToCanvas(copiedFile.toUri()))
            } catch (e: Exception) {
                logAndShowError("EditorViewModel", "Image import failed: ${e.message}")
            }
        }
    }

    private fun handleExport(target: ExportTarget, format: ExportFormat) {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO: implement export via oReader export engine
            Log.d(TAG, "Export requested: target=$target, format=$format")
            val snack = SnackConf(text = "Export not yet implemented", duration = 4000)
            SnackState.globalSnackFlow.emit(snack)
        }
    }

    private fun handleBackgroundChange(type: String, path: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val page = pageRepository.getById(currentPageId) ?: return@launch
            val updatedPage = if (path == null) {
                page.copy(backgroundType = type)
            } else {
                page.copy(background = path, backgroundType = type)
            }
            pageRepository.update(updatedPage)

            val bgPageNum = when (val bgTypeObj = BackgroundType.fromKey(type)) {
                is BackgroundType.Pdf -> bgTypeObj.page
                is BackgroundType.AutoPdf -> {
                    bookId?.let { notebookRepository.getPageIndex(it, currentPageId) } ?: 0
                }
                else -> 0
            }

            _toolbarState.update {
                it.copy(
                    backgroundType = updatedPage.backgroundType,
                    backgroundPath = updatedPage.background,
                    backgroundPageNumber = bgPageNum
                )
            }
            sendCanvasCommand(CanvasCommand.RefreshCanvas)
        }
    }

    private fun handleNavigateToLibrary() {
        viewModelScope.launch(Dispatchers.IO) {
            // TODO: determine parent folder from page
            sendUiEvent(EditorUiEvent.NavigateToLibrary(null))
        }
    }

    private fun handleNavigateToPages() {
        bookId?.let { id ->
            sendUiEvent(EditorUiEvent.NavigateToPages(id))
        }
    }

    // --------------------------------------------------------
    // Drawing State
    // --------------------------------------------------------

    /**
     * Re-evaluates whether drawing should be enabled based on menu and selection states.
     */
    fun updateDrawingState() {
        Log.v(TAG, "updateDrawingState")
        val shouldBeDrawing = _toolbarState.value.isDrawingAllowed
        _toolbarState.update { it.copy(isDrawing = shouldBeDrawing) }
        Log.d(TAG, "Drawing state: $shouldBeDrawing")
        viewModelScope.launch {
            CanvasEventBus.isDrawing.emit(shouldBeDrawing)
        }
    }

    fun onFocusChanged(isFocused: Boolean) {
        if (isFocused) {
            updateDrawingState()
        }
    }

    // --------------------------------------------------------
    // Book / Page Data
    // --------------------------------------------------------

    /**
     * Loads context data for the toolbar (page number, background info, etc.)
     */
    suspend fun loadToolbarState(bookId: String?, pageId: String) {
        Log.v(TAG, "loadToolbarState: bookId=$bookId, pageId=$pageId")
        this.bookId = bookId

        val page = pageRepository.getById(pageId)
        if (page == null) {
            logAndShowError(
                "EditorViewModel",
                "Could not find page",
            )
            fixNotebook(bookId, pageId)
            return
        }
        val book = bookId?.let { notebookRepository.getById(it) }

        val pageIndex = bookId?.let { notebookRepository.getPageIndex(it, pageId) } ?: 0
        val totalPages = book?.pageIds?.size ?: 1

        val backgroundTypeObj = BackgroundType.fromKey(page.backgroundType)
        val bgPageNumber = when (backgroundTypeObj) {
            is BackgroundType.Pdf -> backgroundTypeObj.page
            is BackgroundType.AutoPdf -> {
                bookId?.let { notebookRepository.getPageIndex(it, pageId) } ?: 0
            }
            else -> 0
        }

        _toolbarState.update {
            it.copy(
                notebookId = bookId,
                pageId = pageId,
                isBookActive = bookId != null,
                pageNumberInfo = if (bookId != null) "${(pageIndex ?: 0) + 1}/$totalPages" else "1/1",
                currentPageNumber = pageIndex ?: 0,
                backgroundType = page.backgroundType,
                backgroundPath = page.background,
                backgroundPageNumber = bgPageNumber ?: 0
            )
        }
    }

    private fun saveToolbarState() {
        val currentState = _toolbarState.value
        editorSettingCacheManager.setEditorSettings(
            EditorSettingCacheManager.EditorSettings(
                isToolbarOpen = currentState.isToolbarOpen,
                mode = currentState.mode,
                pen = currentState.pen,
                eraser = currentState.eraser,
                penSettings = currentState.penSettings
            )
        )
    }

    /**
     * Attempts to repair potential inconsistencies in the notebook's data structure.
     */
    suspend fun fixNotebook(bookId: String?, pageId: String) {
        TODO("""I'm not confident in the code below.""" )
//        if (bookId != null) {
//            log.i("Could not find page, Cleaning book")
//            SnackState.globalSnackFlow.tryEmit(
//                SnackConf(
//                    text = "Could not find page, cleaning book", duration = 4000
//                )
//            )
//            appRepository.bookRepository.removePage(bookId, pageId)
//
//        }
    }

    // --------------------------------------------------------
    // Page Navigation (from EditorState)
    // --------------------------------------------------------

    private suspend fun getNextPageId(): String? {
        if (bookId == null) return null
        val book = notebookRepository.getById(bookId!!) ?: return null
        val index = notebookRepository.getPageIndex(bookId!!, currentPageId) ?: return null
        return if (index < book.pageIds.size - 1) book.pageIds[index + 1] else null
    }

    private suspend fun getPreviousPageId(): String? {
        if (bookId == null) return null
        val index = notebookRepository.getPageIndex(bookId!!, currentPageId) ?: return null
        return if (index > 0) {
            val book = notebookRepository.getById(bookId!!) ?: return null
            book.pageIds[index - 1]
        } else null
    }

    fun goToNextPage() {
        Log.v(TAG, "goToNextPage")
        viewModelScope.launch(Dispatchers.IO) {
            getNextPageId()?.let { changePage(it) }
        }
    }

    fun goToPreviousPage() {
        Log.v(TAG, "goToPreviousPage")
        viewModelScope.launch(Dispatchers.IO) {
            getPreviousPageId()?.let { changePage(it) }
        }
    }

    /**
     * Updates the persistence layer and UI state to reflect a change in the currently opened page.
     *
     * This method saves the [newPageId] as the last opened page for the current notebook in the
     * repository. If the page ID has changed, it updates the toolbar state; otherwise, it
     * triggers a UI event to notify the user that the target page is already active.
     *
     * @param newPageId The unique identifier of the page to be set as open.
     */
    private suspend fun updateOpenedPage(newPageId: String) {
        Log.v(TAG, "updateOpenedPage: $newPageId")
        if (bookId != null) {
            notebookRepository.setOpenPageId(bookId!!, newPageId)
        }
        if (newPageId != currentPageId) {
            Log.d(TAG, "Page changed")
            _toolbarState.update { it.copy(pageId = newPageId) }
        } else {
            Log.d(TAG, "Tried to change to same page!")
            val snack = SnackConf(text = "Tried to change to same page!", duration = 4000)
            SnackState.globalSnackFlow.emit(snack)
        }
    }

    /**
     * Changes the current page to the one with the specified [id].
     *
     * @param id The unique identifier of the page to switch to.
     */
    fun changePage(id: String) {
        Log.d(TAG, "Changing page to $id, from $currentPageId")
        viewModelScope.launch(Dispatchers.IO) {
            // 1. Notify the PageView about the change

            // 2. Update the persistent layer

            // 3. Update the UI state
            updateOpenedPage(id)


            // 4. Clean the selection state
            selectionState.reset()
        }
    }

    // --------------------------------------------------------
    // Toolbar State Sync Helpers
    // --------------------------------------------------------

    fun setHasClipboard(hasClipboard: Boolean) {
        _toolbarState.update { it.copy(hasClipboard = hasClipboard) }
    }

    fun setShowResetView(showResetView: Boolean) {
        _toolbarState.update { it.copy(showResetView = showResetView) }
    }

    fun setSelectionActive(active: Boolean) {
        Log.v(TAG, "setSelectionActive: $active")
        if (_toolbarState.value.isSelectionActive != active) {
            if (active) //selection is active, we can directly update it, and skip other checks
                viewModelScope.launch {
                    CanvasEventBus.isDrawing.emit(false)
                }
            _toolbarState.update { it.copy(isSelectionActive = active) }
            if (!active)
                updateDrawingState()
        }
    }

    // --------------------------------------------------------
    // Event / Command Helpers
    // --------------------------------------------------------

    private fun sendUiEvent(event: EditorUiEvent) {
        Log.v(TAG, "sendUiEvent: $event")
        viewModelScope.launch { uiEventChannel.send(event) }
    }

    private fun sendCanvasCommand(command: CanvasCommand) {
        Log.v(TAG, "sendCanvasCommand: $command")
        viewModelScope.launch { canvasCommandChannel.send(command) }
    }

    companion object {
        val DEFAULT_PEN_SETTINGS = mapOf(
            NotebookPen.BALLPEN.penName to NotebookPenSetting(5f, Color.BLACK),
            NotebookPen.REDBALLPEN.penName to NotebookPenSetting(5f, Color.RED),
            NotebookPen.BLUEBALLPEN.penName to NotebookPenSetting(5f, Color.BLUE),
            NotebookPen.GREENBALLPEN.penName to NotebookPenSetting(5f, Color.GREEN),
            NotebookPen.PENCIL.penName to NotebookPenSetting(5f, Color.BLACK),
            NotebookPen.BRUSH.penName to NotebookPenSetting(5f, Color.BLACK),
            NotebookPen.MARKER.penName to NotebookPenSetting(40f, Color.LTGRAY),
            NotebookPen.FOUNTAIN.penName to NotebookPenSetting(5f, Color.BLACK)
        )
    }
}
