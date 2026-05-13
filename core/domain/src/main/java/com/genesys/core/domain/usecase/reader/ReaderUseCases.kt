package com.genesys.core.domain.usecase.reader

import javax.inject.Inject

data class ReaderUseCases @Inject constructor(
    val addSettingsScanFolderUseCase: AddSettingsScanFolderUseCase,
    val getSettingsCurrentUseCase: GetSettingsCurrentUseCase,
    val getSettingsSnapshotUseCase: GetSettingsSnapshotUseCase,
    val listSettingsScanFoldersUseCase: ListSettingsScanFoldersUseCase,
    val loadSettingsUseCase: LoadSettingsUseCase,
    val observeSettingsUseCase: ObserveSettingsUseCase,
    val removeSettingsScanFolderUseCase: RemoveSettingsScanFolderUseCase,
    val saveSettingsUseCase: SaveSettingsUseCase,
    val updateSettingsScanFolderUseCase: UpdateSettingsScanFolderUseCase
)