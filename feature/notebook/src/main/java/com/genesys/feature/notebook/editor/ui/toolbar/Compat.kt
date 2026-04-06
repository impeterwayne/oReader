@file:Suppress("unused")

package com.genesys.feature.notebook.editor.ui.toolbar

import android.util.Log
import com.genesys.feature.notebook.editor.settings.NotebookEditorSettings
import com.genesys.feature.notebook.editor.settings.NotebookSettingsProvider

typealias AppSettings = NotebookEditorSettings

object GlobalAppSettings {
    val current: NotebookEditorSettings
        get() = NotebookSettingsProvider.current
}

fun String.d(message: String) = Log.d(this, message)
fun String.d(message: String, throwable: Throwable) = Log.d(this, message, throwable)
