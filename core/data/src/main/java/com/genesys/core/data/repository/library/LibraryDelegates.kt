package com.genesys.core.data.repository.library

import com.genesys.core.model.library.Book
import com.genesys.core.model.library.LibrarySnapshot
import com.genesys.core.model.library.OpenBookResult

interface LibrarySnapshotLoader {
    fun getLibrarySnapshot(): LibrarySnapshot
}

interface LibraryBookOpener {
    fun openBook(book: Book): OpenBookResult
}
