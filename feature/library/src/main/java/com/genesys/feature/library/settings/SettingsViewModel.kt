package com.genesys.feature.library.settings

import com.genesys.core.common.base.BaseViewModel
import com.genesys.core.common.base.Result
import com.genesys.core.domain.usecase.ai.GetAiModelsUseCase
import com.genesys.core.domain.usecase.ai.GetAiSettingsUseCase
import com.genesys.core.domain.usecase.ai.SaveAiSettingsUseCase
import com.genesys.core.model.ai.AiSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getAiModelsUseCase: GetAiModelsUseCase,
    private val getAiSettingsUseCase: GetAiSettingsUseCase,
    private val saveAiSettingsUseCase: SaveAiSettingsUseCase
) : BaseViewModel<SettingsUiState, SettingsSideEffect, SettingsAction>() {

    override val container = container<SettingsUiState, SettingsSideEffect>(SettingsUiState()) {
        observeSettings()
    }

    private fun observeSettings() = intent {
        getAiSettingsUseCase().collectLatest { settings ->
            reduce {
                state.copy(
                    endpoint = settings.endpoint,
                    apiKey = settings.apiKey,
                    selectedModel = settings.selectedModel
                )
            }
        }
    }

    override fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.UpdateEndpoint -> intent { reduce { state.copy(endpoint = action.endpoint) } }
            is SettingsAction.UpdateApiKey -> intent { reduce { state.copy(apiKey = action.apiKey) } }
            is SettingsAction.SelectModel -> intent { reduce { state.copy(selectedModel = action.modelId) } }
            SettingsAction.FetchModels -> fetchModels()
            SettingsAction.SaveSettings -> saveSettings()
        }
    }

    private fun fetchModels() = intent {
        reduce { state.copy(isLoading = true) }
        when (val result = getAiModelsUseCase()) {
            is Result.Success -> {
                reduce { 
                    state.copy(
                        isLoading = false,
                        availableModels = result.data ?: emptyList()
                    ) 
                }
                postSideEffect(SettingsSideEffect.ShowMessage("Models fetched successfully"))
            }
            is Result.Error -> {
                reduce { state.copy(isLoading = false) }
                postSideEffect(SettingsSideEffect.ShowMessage("Failed to fetch models"))
            }
            else -> {
                // Do nothing for loading or initial since we handled loading above
            }
        }
    }

    private fun saveSettings() = intent {
        saveAiSettingsUseCase(
            AiSettings(
                endpoint = state.endpoint,
                apiKey = state.apiKey,
                selectedModel = state.selectedModel
            )
        )
        postSideEffect(SettingsSideEffect.ShowMessage("Settings saved"))
    }
}
