@file:Suppress("unused")

package com.genesys.feature.notebook.editor.state

import android.util.Log
import com.genesys.core.model.notebook.NotebookImage
import com.genesys.core.model.notebook.NotebookStroke

typealias Stroke = NotebookStroke
typealias Image = NotebookImage

fun String.v(message: String) = Log.v(this, message)
fun String.v(message: String, throwable: Throwable) = Log.v(this, message, throwable)
fun String.d(message: String) = Log.d(this, message)
fun String.d(message: String, throwable: Throwable) = Log.d(this, message, throwable)
fun String.i(message: String) = Log.i(this, message)
fun String.i(message: String, throwable: Throwable) = Log.i(this, message, throwable)
fun String.w(message: String) = Log.w(this, message)
fun String.w(message: String, throwable: Throwable) = Log.w(this, message, throwable)
fun String.e(message: String) = Log.e(this, message)
fun String.e(message: String, throwable: Throwable) = Log.e(this, message, throwable)
