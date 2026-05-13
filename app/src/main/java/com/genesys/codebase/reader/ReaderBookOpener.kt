package com.genesys.codebase.reader

import android.content.Context
import android.net.Uri
import com.genesys.feature.koreader.bridge.KoreaderStorageBridge
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ReaderBookOpener @Inject constructor(
    @ApplicationContext private val context: Context,
    private val storageBridge: KoreaderStorageBridge
) {

    fun openBook(book: ReaderBook): ReaderLibraryRepository.OpenBookResult {
        return when (book.source) {
            ReaderBookSource.ManagedCopy -> {
                val file = File(book.filePath)
                if (file.exists()) {
                    ReaderLibraryRepository.OpenBookResult.Available(file.absolutePath, isStaged = false)
                } else {
                    ReaderLibraryRepository.OpenBookResult.Unavailable("Managed file is missing")
                }
            }

            ReaderBookSource.SafFolder -> {
                val contentUri = book.contentUri?.let(Uri::parse)
                    ?: return ReaderLibraryRepository.OpenBookResult.Unavailable("Book content URI is unavailable")
                when (val resolved = storageBridge.resolveUri(context, contentUri)) {
                    is KoreaderStorageBridge.ResolvedDocument.Available -> {
                        ReaderLibraryRepository.OpenBookResult.Available(resolved.filePath, resolved.isStaged)
                    }
                    is KoreaderStorageBridge.ResolvedDocument.Unavailable -> {
                        ReaderLibraryRepository.OpenBookResult.Unavailable(resolved.reason)
                    }
                }
            }
        }
    }
}
