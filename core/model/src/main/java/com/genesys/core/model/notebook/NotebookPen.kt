package com.genesys.core.model.notebook

enum class NotebookPen(val penName: String) {
    BALLPEN("BALLPEN"),
    REDBALLPEN("REDBALLPEN"),
    GREENBALLPEN("GREENBALLPEN"),
    BLUEBALLPEN("BLUEBALLPEN"),
    PENCIL("PENCIL"),
    BRUSH("BRUSH"),
    MARKER("MARKER"),
    FOUNTAIN("FOUNTAIN"),
    DASHED("DASHED");

    companion object {
        fun fromName(name: String?): NotebookPen {
            return entries.find { it.penName.equals(name, ignoreCase = true) } ?: BALLPEN
        }

        /** Alias matching Notable's original `Pen.fromString()` for ported call-sites. */
        fun fromString(name: String?) = fromName(name)
    }
}

data class NotebookPenSetting(
    val strokeSize: Float,
    val color: Int
)

typealias NotebookNamedSettings = Map<String, NotebookPenSetting>
