package com.genesys.feature.notebook.editor.settings

import androidx.compose.runtime.mutableStateOf
import com.genesys.core.model.notebook.NotebookSettings

// Define the target page size (A4 in points: 595 x 842)
const val A4_WIDTH = 595
const val A4_HEIGHT = 842
const val BUTTON_SIZE = 37

/**
 * Global singleton providing access to the current notebook editor settings.
 * This is a bridge between the oReader [NotebookSettings] model and the
 * editor components that were ported from Notable.
 */
object NotebookSettingsProvider {
    private val _current = mutableStateOf(NotebookEditorSettings())
    val current: NotebookEditorSettings
        get() = _current.value

    fun update(settings: NotebookEditorSettings) {
        _current.value = settings
    }

    fun updateFromModel(model: NotebookSettings) {
        _current.value = NotebookEditorSettings.fromNotebookSettings(model)
    }
}

/**
 * Runtime settings for the notebook editor, bridging [NotebookSettings] model
 * to the format expected by ported Notable components.
 *
 * Note: this maps 1:1 to Notable's `AppSettings` data class, adapted to
 * reference oReader's `NotebookSettings` model.
 */
data class NotebookEditorSettings(
    // General
    val version: Int = 1,
    val monitorBgFiles: Boolean = false,
    val defaultNativeTemplate: String = "blank",
    val quickNavPages: List<String> = listOf(),
    val neoTools: Boolean = false,
    val scribbleToEraseEnabled: Boolean = false,
    val toolbarPosition: Position = Position.Top,
    val smoothScroll: Boolean = true,
    val continuousZoom: Boolean = false,
    val continuousStrokeSlider: Boolean = false,
    val monochromeMode: Boolean = false,
    val paginatePdf: Boolean = true,
    val visualizePdfPagination: Boolean = false,

    // Gestures
    val doubleTapAction: GestureAction? = defaultDoubleTapAction,
    val twoFingerTapAction: GestureAction? = defaultTwoFingerTapAction,
    val swipeLeftAction: GestureAction? = defaultSwipeLeftAction,
    val swipeRightAction: GestureAction? = defaultSwipeRightAction,
    val twoFingerSwipeLeftAction: GestureAction? = defaultTwoFingerSwipeLeftAction,
    val twoFingerSwipeRightAction: GestureAction? = defaultTwoFingerSwipeRightAction,
    val holdAction: GestureAction? = defaultHoldAction,
    val enableQuickNav: Boolean = true,

    // Debug / rendering
    val showWelcome: Boolean = true,
    val debugMode: Boolean = false,
    val simpleRendering: Boolean = false,
    val openGLRendering: Boolean = true,
    val muPdfRendering: Boolean = true,
    val destructiveMigrations: Boolean = false,
) {

    companion object {
        val defaultDoubleTapAction get() = GestureAction.Undo
        val defaultTwoFingerTapAction get() = GestureAction.ChangeTool
        val defaultSwipeLeftAction get() = GestureAction.NextPage
        val defaultSwipeRightAction get() = GestureAction.PreviousPage
        val defaultTwoFingerSwipeLeftAction get() = GestureAction.ToggleZen
        val defaultTwoFingerSwipeRightAction get() = GestureAction.ToggleZen
        val defaultHoldAction get() = GestureAction.Select

        fun fromNotebookSettings(s: NotebookSettings) = NotebookEditorSettings(
            version = s.version,
            defaultNativeTemplate = s.defaultNativeTemplate,
            quickNavPages = s.quickNavPages,
            neoTools = s.neoTools,
            scribbleToEraseEnabled = s.scribbleToEraseEnabled,
            toolbarPosition = when (s.toolbarPosition) {
                NotebookSettings.ToolbarPosition.Top -> Position.Top
                NotebookSettings.ToolbarPosition.Bottom -> Position.Bottom
            },
            smoothScroll = s.smoothScroll,
            continuousZoom = s.continuousZoom,
            continuousStrokeSlider = s.continuousStrokeSlider,
            monochromeMode = s.monochromeMode,
            paginatePdf = s.paginatePdf,
            visualizePdfPagination = s.visualizePdfPagination,
            doubleTapAction = s.doubleTapAction?.let { mapGestureAction(it) },
            twoFingerTapAction = s.twoFingerTapAction?.let { mapGestureAction(it) },
            swipeLeftAction = s.swipeLeftAction?.let { mapGestureAction(it) },
            swipeRightAction = s.swipeRightAction?.let { mapGestureAction(it) },
            twoFingerSwipeLeftAction = s.twoFingerSwipeLeftAction?.let { mapGestureAction(it) },
            twoFingerSwipeRightAction = s.twoFingerSwipeRightAction?.let { mapGestureAction(it) },
            holdAction = s.holdAction?.let { mapGestureAction(it) },
            enableQuickNav = s.enableQuickNav,
            showWelcome = s.showWelcome,
            debugMode = s.debugMode,
            simpleRendering = s.simpleRendering,
            openGLRendering = s.openGLRendering,
            muPdfRendering = s.muPdfRendering,
            destructiveMigrations = s.destructiveMigrations,
        )

        private fun mapGestureAction(a: NotebookSettings.GestureAction): GestureAction =
            when (a) {
                NotebookSettings.GestureAction.Undo -> GestureAction.Undo
                NotebookSettings.GestureAction.Redo -> GestureAction.Redo
                NotebookSettings.GestureAction.PreviousPage -> GestureAction.PreviousPage
                NotebookSettings.GestureAction.NextPage -> GestureAction.NextPage
                NotebookSettings.GestureAction.ChangeTool -> GestureAction.ChangeTool
                NotebookSettings.GestureAction.ToggleZen -> GestureAction.ToggleZen
                NotebookSettings.GestureAction.Select -> GestureAction.Select
            }
    }

    enum class GestureAction {
        Undo, Redo, PreviousPage, NextPage, ChangeTool, ToggleZen, Select
    }

    enum class Position {
        Top, Bottom,
    }
}
