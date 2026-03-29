package com.genesys.feature.koreader.host

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import org.koreader.launcher.MainActivity as LauncherActivity
import org.koreader.launcher.R
import timber.log.Timber
import java.io.File

/**
 * Thin launcher boundary between the Compose shell and the vendored KOReader
 * NativeActivity.
 */
class KoreaderActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val targetIntent = when (val launchMode = LaunchMode.fromIntent(intent)) {
            is LaunchMode.FileManager -> {
                Timber.i("KOReader: launching file manager mode")
                Intent(this, LauncherActivity::class.java)
            }
            is LaunchMode.Resume -> {
                Timber.i("KOReader: launching resume mode")
                Intent(this, LauncherActivity::class.java)
            }
            is LaunchMode.OpenDocument -> {
                val file = File(launchMode.filePath)
                if (!file.exists()) {
                    Timber.w("KOReader document path is missing: ${launchMode.filePath}")
                    Toast.makeText(
                        this,
                        getString(R.string.koreader_error_document_missing),
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                    return
                }

                Timber.i("KOReader: opening document: ${launchMode.filePath}")
                Intent(this, LauncherActivity::class.java).apply {
                    putExtra(LauncherActivity.EXTRA_OPEN_FILE_PATH, file.absolutePath)
                }
            }
        }

        startActivity(targetIntent)
        finish()
    }

    /**
     * KOReader launch modes, passed via Intent extras.
     */
    sealed class LaunchMode {
        /** Open KOReader's file manager */
        data object FileManager : LaunchMode()

        /** Resume the last-read document */
        data object Resume : LaunchMode()

        /** Open a specific document */
        data class OpenDocument(val filePath: String) : LaunchMode()

        companion object {
            private const val EXTRA_MODE = "koreader_launch_mode"
            private const val EXTRA_FILE_PATH = "koreader_file_path"

            private const val MODE_FILE_MANAGER = "file_manager"
            private const val MODE_RESUME = "resume"
            private const val MODE_OPEN_DOCUMENT = "open_document"

            fun fromIntent(intent: Intent): LaunchMode {
                return when (intent.getStringExtra(EXTRA_MODE)) {
                    MODE_FILE_MANAGER -> FileManager
                    MODE_RESUME -> Resume
                    MODE_OPEN_DOCUMENT -> {
                        val path = intent.getStringExtra(EXTRA_FILE_PATH)
                            ?: return FileManager
                        OpenDocument(path)
                    }
                    else -> FileManager
                }
            }

            fun toIntent(context: Context, mode: LaunchMode): Intent {
                return Intent(context, KoreaderActivity::class.java).apply {
                    when (mode) {
                        is FileManager -> {
                            putExtra(EXTRA_MODE, MODE_FILE_MANAGER)
                        }
                        is Resume -> {
                            putExtra(EXTRA_MODE, MODE_RESUME)
                        }
                        is OpenDocument -> {
                            putExtra(EXTRA_MODE, MODE_OPEN_DOCUMENT)
                            putExtra(EXTRA_FILE_PATH, mode.filePath)
                        }
                    }
                }
            }
        }
    }
}
