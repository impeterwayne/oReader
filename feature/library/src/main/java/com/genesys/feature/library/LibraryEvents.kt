package com.genesys.feature.library

import com.genesys.core.model.library.*
import com.genesys.core.domain.repository.library.*

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LibraryEvents @Inject constructor() {
    private val _updates = MutableSharedFlow<Unit>(
        replay = 1,
        extraBufferCapacity = 1
    )

    val updates = _updates.asSharedFlow()

    fun notifyChanged() {
        _updates.tryEmit(Unit)
    }
}
