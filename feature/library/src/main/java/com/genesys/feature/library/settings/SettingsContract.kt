package com.genesys.feature.library.settings

import com.genesys.core.common.base.mvi.Action
import com.genesys.core.common.base.mvi.SideEffect
import com.genesys.core.common.base.mvi.UiState
import com.genesys.core.model.ai.AiModel

data class SettingsUiState(
    val endpoint: String = "",
    val apiKey: String = "",
    val selectedModel: String = "",
    val availableModels: List<AiModel> = emptyList(),
    val isLoading: Boolean = false
) : UiState

sealed interface SettingsAction : Action {
    data class UpdateEndpoint(val endpoint: String) : SettingsAction
    data class UpdateApiKey(val apiKey: String) : SettingsAction
    data class SelectModel(val modelId: String) : SettingsAction
    data object FetchModels : SettingsAction
    data object SaveSettings : SettingsAction
}

sealed interface SettingsSideEffect : SideEffect {
    data class ShowMessage(val message: String) : SettingsSideEffect
}
