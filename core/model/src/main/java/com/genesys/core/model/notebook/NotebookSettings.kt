package com.genesys.core.model.notebook

/**
 * Notebook-specific application settings.
 *
 * Ported from Notable's AppSettings. Persisted via the notebook key-value
 * store under a well-known key. Serialisation to/from JSON is handled in
 * the data layer using Moshi.
 */
data class NotebookSettings(
    val version: Int = 1,

    // ── General ────────────────────────────────────────
    val monitorBackgroundFiles: Boolean = false,
    val defaultNativeTemplate: String = "blank",
    val quickNavPages: List<String> = emptyList(),
    val neoTools: Boolean = false,
    val scribbleToEraseEnabled: Boolean = false,
    val toolbarPosition: ToolbarPosition = ToolbarPosition.Top,
    val smoothScroll: Boolean = true,
    val continuousZoom: Boolean = false,
    val continuousStrokeSlider: Boolean = false,
    val monochromeMode: Boolean = false,
    val paginatePdf: Boolean = true,
    val visualizePdfPagination: Boolean = false,

    // ── Gestures ───────────────────────────────────────
    val doubleTapAction: GestureAction? = GestureAction.Undo,
    val twoFingerTapAction: GestureAction? = GestureAction.ChangeTool,
    val swipeLeftAction: GestureAction? = GestureAction.NextPage,
    val swipeRightAction: GestureAction? = GestureAction.PreviousPage,
    val twoFingerSwipeLeftAction: GestureAction? = GestureAction.ToggleZen,
    val twoFingerSwipeRightAction: GestureAction? = GestureAction.ToggleZen,
    val holdAction: GestureAction? = GestureAction.Select,
    val enableQuickNav: Boolean = true,

    // ── Debug / rendering ──────────────────────────────
    val showWelcome: Boolean = true,
    val debugMode: Boolean = false,
    val simpleRendering: Boolean = false,
    val openGLRendering: Boolean = true,
    val muPdfRendering: Boolean = true,
    val destructiveMigrations: Boolean = false,
) {
    enum class GestureAction {
        Undo, Redo, PreviousPage, NextPage, ChangeTool, ToggleZen, Select
    }

    enum class ToolbarPosition {
        Top, Bottom
    }

    companion object {
        /** Key used to persist this object in the notebook KV store. */
        const val KV_KEY = "NOTEBOOK_APP_SETTINGS"
    }
}
