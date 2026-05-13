package com.genesys.core.domain.repository.library

import com.genesys.core.model.library.LibrarySnapshot

interface LibrarySnapshotLoader {
    fun getLibrarySnapshot(): LibrarySnapshot
}
