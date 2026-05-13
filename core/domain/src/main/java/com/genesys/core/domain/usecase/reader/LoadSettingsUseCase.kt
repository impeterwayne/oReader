package com.genesys.core.domain.usecase.reader

import com.genesys.core.domain.repository.reader.SettingsRepository
import com.genesys.core.model.reader.ReaderScanFolder
import com.genesys.core.model.reader.ReaderSettings
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LoadSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): Unit {
        return settingsRepository.load()
    }
}
