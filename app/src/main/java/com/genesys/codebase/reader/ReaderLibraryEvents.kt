package com.genesys.codebase.reader

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReaderLibraryEvents @Inject constructor() {
    private val _updates = MutableSharedFlow<Unit>(
        replay = 1,
        extraBufferCapacity = 1
    )

    val updates = _updates.asSharedFlow()

    fun notifyChanged() {
        _updates.tryEmit(Unit)
    }
}
