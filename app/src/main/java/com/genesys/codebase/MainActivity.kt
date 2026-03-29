package com.genesys.codebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.codebase.navigation.AppShell
import com.genesys.codebase.reader.ReaderLibraryEvents
import com.genesys.codebase.reader.ReaderLibraryRepository
import com.genesys.feature.koreader.bridge.KoreaderStorageBridge
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ktx.immersionBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var storageBridge: KoreaderStorageBridge

    @Inject
    lateinit var readerLibraryRepository: ReaderLibraryRepository

    @Inject
    lateinit var readerLibraryEvents: ReaderLibraryEvents

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        immersionBar {
            transparentBar()
            statusBarDarkFont(true)
            fitsSystemWindows(false)
            hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
            navigationBarEnable(false)
        }

        enableEdgeToEdge()

        // Handle incoming document intents (ACTION_VIEW, SEND)
        if (savedInstanceState == null) {
            handleIncomingIntent(intent)
        }

        setContent {
            GenesysTheme {
                AppShell()
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIncomingIntent(intent)
    }

    /**
     * Add incoming documents to the in-app reader library instead of opening
     * KOReader immediately.
     */
    private fun handleIncomingIntent(intent: Intent) {
        val action = intent.action
        if (action == Intent.ACTION_VIEW || action == Intent.ACTION_SEND) {
            Timber.i("Received document intent: action=$action, data=${intent.data}")

            lifecycleScope.launch {
                val message = withContext(Dispatchers.IO) {
                    when (val resolved = storageBridge.resolveIntent(this@MainActivity, intent)) {
                        is KoreaderStorageBridge.ResolvedDocument.Available -> {
                            if (resolved.isStaged || !readerLibraryRepository.hasSharedStorageAccess()) {
                                readerLibraryRepository.importFile(resolved.filePath)
                                    .also {
                                        if (resolved.isStaged) {
                                            File(resolved.filePath).delete()
                                        }
                                    }
                                    .toMessage()
                            } else {
                                "Book available in your library"
                            }
                        }
                        is KoreaderStorageBridge.ResolvedDocument.Unavailable -> {
                            Timber.w("Could not resolve document: ${resolved.reason}")
                            null
                        }
                    }
                }

                if (message != null) {
                    readerLibraryEvents.notifyChanged()
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun ReaderLibraryRepository.ImportResult.toMessage(): String? {
        val parts = buildList {
            if (importedCount > 0) {
                add(
                    if (importedCount == 1) {
                        "Added 1 book to your library"
                    } else {
                        "Added $importedCount books to your library"
                    }
                )
            }
            if (skippedUnsupported.isNotEmpty()) {
                add("Skipped ${skippedUnsupported.size} unsupported file(s)")
            }
            if (failed.isNotEmpty()) {
                add("Failed ${failed.size} file import(s)")
            }
        }

        return parts.takeIf { it.isNotEmpty() }?.joinToString(". ")
    }
}
