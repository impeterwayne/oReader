package com.genesys.core.model.library

data class LibrarySnapshot(
    val books: List<Book>,
    val selectedFolders: List<LibraryFolder>,
    val invalidFolders: List<LibraryFolder>
)
