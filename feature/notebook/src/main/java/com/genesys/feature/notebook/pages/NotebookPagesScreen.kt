package com.genesys.feature.notebook.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.genesys.core.designsystem.component.GenesysPanel
import com.genesys.core.designsystem.component.GenesysPanelTone
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.core.model.notebook.Notebook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

object NotebookPagesDestination {
    const val route = "notebook-pages"
    const val BOOK_ID_ARG = "bookId"
    const val routeWithArgs = "$route/{$BOOK_ID_ARG}"

    fun createRoute(bookId: String): String = "$route/$bookId"
}

data class NotebookPagesUiState(
    val isLoading: Boolean = true,
    val notebook: Notebook? = null
)

@HiltViewModel
class NotebookPagesViewModel @Inject constructor(
    private val notebookRepository: NotebookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotebookPagesUiState())
    val uiState: StateFlow<NotebookPagesUiState> = _uiState.asStateFlow()

    fun load(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val notebook = notebookRepository.getById(bookId)
            withContext(Dispatchers.Main) {
                _uiState.value = NotebookPagesUiState(
                    isLoading = false,
                    notebook = notebook
                )
            }
        }
    }
}

@Composable
fun NotebookPagesRoute(
    bookId: String,
    onBack: () -> Unit,
    onOpenPage: (pageId: String, bookId: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NotebookPagesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    androidx.compose.runtime.LaunchedEffect(bookId) {
        viewModel.load(bookId)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GenesysTheme.colors.surfaceDim)
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(key = "header") {
            Column(
                modifier = Modifier.statusBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                GenesysText(
                    text = uiState.notebook?.title ?: "Pages",
                    style = GenesysTheme.typography.titleLarge,
                    color = GenesysTheme.colors.primary
                )
                GenesysText(
                    text = "Notebook page list for the current book.",
                    style = GenesysTheme.typography.bodyLarge,
                    color = GenesysTheme.colors.outline
                )
            }
        }

        item(key = "back") {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                GenesysPrimaryButton(
                    text = "Back",
                    onClick = onBack
                )
            }
        }

        if (uiState.isLoading) {
            item(key = "loading") {
                GenesysPanel(
                    modifier = Modifier.fillMaxWidth(),
                    tone = GenesysPanelTone.Raised
                ) {
                    GenesysText(
                        text = "Loading pages",
                        style = GenesysTheme.typography.titleMedium
                    )
                }
            }
        }

        val notebook = uiState.notebook
        if (!uiState.isLoading && notebook != null) {
            itemsIndexed(
                items = notebook.pageIds,
                key = { _, pageId -> pageId }
            ) { index, pageId ->
                val isOpen = pageId == notebook.openPageId
                GenesysPanel(
                    modifier = Modifier.fillMaxWidth(),
                    tone = if (isOpen) GenesysPanelTone.Heavy else GenesysPanelTone.Raised,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    onClick = { onOpenPage(pageId, notebook.id) }
                ) {
                    GenesysText(
                        text = "Page ${index + 1}",
                        style = GenesysTheme.typography.titleMedium
                    )
                    GenesysText(
                        text = if (isOpen) "Current page" else pageId,
                        style = GenesysTheme.typography.bodyMedium,
                        color = GenesysTheme.colors.outline
                    )
                }
            }
        }
    }
}
