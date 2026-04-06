package com.genesys.feature.notebook.utils

import android.util.Log

/**
 * Logs the current call stack for debugging purposes.
 * Replaces Notable's `logCallStack` utility.
 */
fun logCallStack(tag: String) {
    val stackTrace = Thread.currentThread().stackTrace
        .drop(3) // Skip getStackTrace, logCallStack, and caller
        .take(10) // Limit depth
        .joinToString("\n  ") { "${it.className}.${it.methodName}:${it.lineNumber}" }
    Log.d(tag, "Call stack:\n  $stackTrace")
}

/**
 * Simple timing utility for performance measurement.
 * Replaces Notable's `Timing` utility.
 */
object Timing {
    private val starts = mutableMapOf<String, Long>()

    fun start(label: String) {
        starts[label] = System.nanoTime()
    }

    fun end(label: String): Long {
        val start = starts.remove(label) ?: return 0
        val elapsed = (System.nanoTime() - start) / 1_000_000
        Log.d("Timing", "$label: ${elapsed}ms")
        return elapsed
    }
}
