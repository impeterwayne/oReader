package com.genesys.feature.koreader.host

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

/**
 * Activity result contract for launching KOReader in file-manager mode.
 *
 * Usage:
 * ```kotlin
 * val launcher = registerForActivityResult(KoreaderFileManagerContract()) { result ->
 *     // Handle result (e.g., user selected a document)
 * }
 * launcher.launch(Unit)
 * ```
 */
class KoreaderFileManagerContract : ActivityResultContract<Unit, KoreaderResult>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        return KoreaderActivity.LaunchMode.toIntent(
            context,
            KoreaderActivity.LaunchMode.FileManager
        )
    }

    override fun parseResult(resultCode: Int, intent: Intent?): KoreaderResult {
        return when (resultCode) {
            Activity.RESULT_OK -> KoreaderResult.Completed
            Activity.RESULT_CANCELED -> KoreaderResult.Cancelled
            else -> KoreaderResult.Error("Unknown result code: $resultCode")
        }
    }
}

/**
 * Activity result contract for resuming the last-read document in KOReader.
 *
 * Usage:
 * ```kotlin
 * val launcher = registerForActivityResult(KoreaderResumeContract()) { result ->
 *     // Handle result
 * }
 * launcher.launch(Unit)
 * ```
 */
class KoreaderResumeContract : ActivityResultContract<Unit, KoreaderResult>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        return KoreaderActivity.LaunchMode.toIntent(
            context,
            KoreaderActivity.LaunchMode.Resume
        )
    }

    override fun parseResult(resultCode: Int, intent: Intent?): KoreaderResult {
        return when (resultCode) {
            Activity.RESULT_OK -> KoreaderResult.Completed
            Activity.RESULT_CANCELED -> KoreaderResult.Cancelled
            else -> KoreaderResult.Error("Unknown result code: $resultCode")
        }
    }
}

/**
 * Activity result contract for opening a specific document in KOReader.
 *
 * Usage:
 * ```kotlin
 * val launcher = registerForActivityResult(KoreaderOpenDocumentContract()) { result ->
 *     // Handle result
 * }
 * launcher.launch("/path/to/document.epub")
 * ```
 */
class KoreaderOpenDocumentContract : ActivityResultContract<String, KoreaderResult>() {
    override fun createIntent(context: Context, input: String): Intent {
        return KoreaderActivity.LaunchMode.toIntent(
            context,
            KoreaderActivity.LaunchMode.OpenDocument(input)
        )
    }

    override fun parseResult(resultCode: Int, intent: Intent?): KoreaderResult {
        return when (resultCode) {
            Activity.RESULT_OK -> KoreaderResult.Completed
            Activity.RESULT_CANCELED -> KoreaderResult.Cancelled
            else -> KoreaderResult.Error("Unknown result code: $resultCode")
        }
    }
}

/**
 * Result type for all KOReader launch contracts.
 */
sealed class KoreaderResult {
    /** KOReader session completed normally (user pressed back or closed). */
    data object Completed : KoreaderResult()

    /** User cancelled before KOReader could start. */
    data object Cancelled : KoreaderResult()

    /** An error occurred during the KOReader session. */
    data class Error(val message: String) : KoreaderResult()
}
