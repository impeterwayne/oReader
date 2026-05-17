package com.genesys.feature.library.settings

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SettingsScreenRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.container.stateFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is SettingsSideEffect.ShowMessage -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    SettingsScreen(
        state = uiState,
        onAction = viewModel::onAction,
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@Composable
private fun GenesysTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(modifier = modifier) {
        GenesysText(
            text = label,
            style = GenesysTheme.typography.labelMedium,
            color = GenesysTheme.colors.outline
        )
        Spacer(Modifier.height(GenesysTheme.spacing.xs))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(GenesysTheme.colors.surface, GenesysTheme.shapes.medium)
                .border(GenesysTheme.strokes.thin, GenesysTheme.colors.outline, GenesysTheme.shapes.medium)
                .padding(horizontal = GenesysTheme.spacing.md, vertical = GenesysTheme.spacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                readOnly = readOnly,
                singleLine = true,
                keyboardOptions = keyboardOptions,
                textStyle = GenesysTheme.typography.bodyMedium.copy(color = GenesysTheme.colors.onSurface)
            )
            if (trailingIcon != null) {
                trailingIcon()
            }
        }
    }
}

@Composable
private fun SettingsScreen(
    state: SettingsUiState,
    onAction: (SettingsAction) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GenesysTheme.colors.background)
            .statusBarsPadding()
            .padding(GenesysTheme.spacing.md),
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.lg)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .clickable { onBackClick() }
                    .padding(end = GenesysTheme.spacing.md),
                tint = GenesysTheme.colors.onSurface
            )
            GenesysText(
                text = "AI Settings",
                style = GenesysTheme.typography.titleLarge,
                color = GenesysTheme.colors.onSurface
            )
        }

        GenesysTextField(
            value = state.endpoint,
            onValueChange = { onAction(SettingsAction.UpdateEndpoint(it)) },
            label = "Endpoint URL",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )

        GenesysTextField(
            value = state.apiKey,
            onValueChange = { onAction(SettingsAction.UpdateApiKey(it)) },
            label = "API Key",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        var expanded by remember { mutableStateOf(false) }
        
        Box {
            Box(modifier = Modifier.clickable { expanded = !expanded }) {
                GenesysTextField(
                    value = state.selectedModel.ifEmpty { "Select a model" },
                    onValueChange = {},
                    readOnly = true,
                    label = "Selected Model",
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Fetch Models",
                                modifier = Modifier
                                    .clickable { onAction(SettingsAction.FetchModels) }
                                    .padding(GenesysTheme.spacing.sm),
                                tint = GenesysTheme.colors.primary
                            )
                            Spacer(modifier = Modifier.width(GenesysTheme.spacing.xs))
                            GenesysText(
                                text = if (expanded) "▲" else "▼",
                                style = GenesysTheme.typography.bodyMedium,
                                color = GenesysTheme.colors.outline
                            )
                        }
                    }
                )
            }

            if (expanded) {
                Popup(
                    alignment = Alignment.TopStart,
                    properties = PopupProperties(focusable = true),
                    onDismissRequest = { expanded = false }
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 70.dp) // Offset below the text field roughly
                            .fillMaxWidth(0.9f)
                            .heightIn(max = 250.dp)
                            .background(GenesysTheme.colors.surface, GenesysTheme.shapes.medium)
                            .border(GenesysTheme.strokes.thin, GenesysTheme.colors.outline, GenesysTheme.shapes.medium)
                    ) {
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            if (state.availableModels.isEmpty()) {
                                item {
                                    Box(modifier = Modifier.padding(GenesysTheme.spacing.md)) {
                                        GenesysText(
                                            text = if (state.isLoading) "Fetching..." else "No models available",
                                            style = GenesysTheme.typography.bodyMedium,
                                            color = GenesysTheme.colors.outline
                                        )
                                    }
                                }
                            } else {
                                items(state.availableModels) { model ->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                onAction(SettingsAction.SelectModel(model.id))
                                                expanded = false
                                            }
                                            .padding(GenesysTheme.spacing.md)
                                    ) {
                                        GenesysText(
                                            text = model.id,
                                            style = GenesysTheme.typography.bodyMedium,
                                            color = GenesysTheme.colors.onSurface
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        GenesysPrimaryButton(
            text = "Save Settings",
            onClick = { onAction(SettingsAction.SaveSettings) },
            modifier = Modifier.fillMaxWidth(),
            allCaps = false
        )
    }
}
