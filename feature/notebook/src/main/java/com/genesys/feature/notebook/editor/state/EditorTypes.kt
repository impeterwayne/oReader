package com.genesys.feature.notebook.editor.state

/**
 * Drawing mode for the editor.
 */
enum class Mode {
    Draw, Erase, Select, Line
}

/**
 * Placement mode for selection operations.
 * If state is Move then applySelectionDisplace() will delete original strokes and images.
 */
enum class PlacementMode {
    Move, Paste
}

/**
 * Eraser types.
 */
enum class Eraser(val eraserName: String) {
    PEN("PEN"), SELECT("SELECT"),
}

/**
 * Singleton holding clipboard content across EditorState instances.
 */
object Clipboard {
    var content: ClipboardContent? = null
}
