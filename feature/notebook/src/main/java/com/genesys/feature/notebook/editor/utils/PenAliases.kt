@file:Suppress("unused")
package com.genesys.feature.notebook.editor.utils

import com.genesys.core.model.notebook.NotebookPen
import com.genesys.core.model.notebook.NotebookPenSetting

/**
 * Type aliases for backward compatibility.
 * The ported code references Pen, PenSetting, and Eraser from the utils package.
 * The canonical types now live in core:model, so we alias them here.
 */
typealias Pen = NotebookPen
typealias PenSetting = NotebookPenSetting
