package com.genesys.core.domain.repository.library

import com.genesys.core.model.library.Book
import com.genesys.core.model.library.OpenBookResult

interface LibraryBookOpener {
    fun openBook(book: Book): OpenBookResult
}
