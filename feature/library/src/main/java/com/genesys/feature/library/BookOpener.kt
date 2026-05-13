package com.genesys.feature.library

import android.content.Context
import android.net.Uri
import com.genesys.core.domain.repository.library.LibraryBookOpener
import com.genesys.core.model.library.Book
import com.genesys.core.model.library.BookSource
import com.genesys.core.model.library.OpenBookResult
import com.genesys.feature.koreader.bridge.KoreaderStorageBridge
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
internal class BookOpener @Inject constructor(
    @ApplicationContext private val context: Context,
    private val storageBridge: KoreaderStorageBridge
) : LibraryBookOpener {

    override fun openBook(book: Book): OpenBookResult {
        return when (book.source) {
            BookSource.ManagedCopy -> {
                val file = File(book.filePath)
                if (file.exists()) {
                    OpenBookResult.Available(file.absolutePath, isStaged = false)
                } else {
                    OpenBookResult.Unavailable("Managed file is missing")
                }
            }

            BookSource.SafFolder -> {
                val contentUri = book.contentUri?.let(Uri::parse)
                    ?: return OpenBookResult.Unavailable("Book content URI is unavailable")
                when (val resolved = storageBridge.resolveUri(context, contentUri)) {
                    is KoreaderStorageBridge.ResolvedDocument.Available -> {
                        OpenBookResult.Available(resolved.filePath, resolved.isStaged)
                    }
                    is KoreaderStorageBridge.ResolvedDocument.Unavailable -> {
                        OpenBookResult.Unavailable(resolved.reason)
                    }
                }
            }
        }
    }
}
