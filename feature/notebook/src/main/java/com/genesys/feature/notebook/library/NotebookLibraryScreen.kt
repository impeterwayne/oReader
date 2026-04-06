package com.genesys.feature.notebook.library

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.core.model.notebook.Notebook
import com.genesys.core.model.notebook.NotebookPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class NotebookLibraryUiState(
    val isLoading: Boolean = true,
    val notebooks: List<Notebook> = emptyList(),
    val quickPages: List<NotebookPage> = emptyList()
)

@HiltViewModel
class NotebookLibraryViewModel @Inject constructor(
    private val notebookRepository: NotebookRepository,
    private val pageRepository: NotebookPageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotebookLibraryUiState())
    val uiState: StateFlow<NotebookLibraryUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                notebookRepository.observeInFolder(null),
                pageRepository.observeStandalonePages(null)
            ) { notebooks, quickPages ->
                NotebookLibraryUiState(
                    isLoading = false,
                    notebooks = notebooks,
                    quickPages = quickPages
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun openNotebook(notebookId: String, onOpen: (String, String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val notebook = notebookRepository.getById(notebookId) ?: return@launch
            val targetPageId = notebook.openPageId ?: notebook.pageIds.firstOrNull() ?: run {
                val newPage = notebook.newPage()
                pageRepository.create(newPage)
                notebookRepository.addPage(notebook.id, newPage.id)
                notebookRepository.setOpenPageId(notebook.id, newPage.id)
                newPage.id
            }
            withContext(Dispatchers.Main) {
                onOpen(targetPageId, notebook.id)
            }
        }
    }

    fun createNotebook(onOpen: (String, String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val notebook = Notebook()
            notebookRepository.create(notebook)
            val created = notebookRepository.getById(notebook.id) ?: return@launch
            val targetPageId = created.openPageId ?: created.pageIds.firstOrNull() ?: return@launch
            withContext(Dispatchers.Main) {
                onOpen(targetPageId, created.id)
            }
        }
    }

    fun createQuickPage(onOpen: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val page = NotebookPage()
            pageRepository.create(page)
            withContext(Dispatchers.Main) {
                onOpen(page.id)
            }
        }
    }
}

@Composable
fun NotebookLibraryRoute(
    onOpenNotebook: (pageId: String, bookId: String?) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NotebookLibraryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GenesysTheme.colors.surfaceDim)
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.statusBarsPadding()
            ) {
                GenesysText(
                    text = "Notebook Library",
                    style = GenesysTheme.typography.titleLarge,
                    color = GenesysTheme.colors.primary
                )
                GenesysText(
                    text = "This route now opens real notebooks and quick pages from local storage instead of a sample host.",
                    style = GenesysTheme.typography.bodyLarge,
                    color = GenesysTheme.colors.outline
                )
            }
        }

        item(key = "actions") {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                GenesysPrimaryButton(
                    text = "New notebook",
                    onClick = {
                        viewModel.createNotebook { pageId, bookId ->
                            onOpenNotebook(pageId, bookId)
                        }
                    }
                )
                GenesysPrimaryButton(
                    text = "Quick page",
                    onClick = {
                        viewModel.createQuickPage { pageId ->
                            onOpenNotebook(pageId, null)
                        }
                    }
                )
            }
        }

        if (uiState.isLoading) {
            item(key = "loading") {
                GenesysPanel(
                    modifier = Modifier.fillMaxWidth(),
                    tone = GenesysPanelTone.Raised,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    GenesysText(
                        text = "Loading notebooks",
                        style = GenesysTheme.typography.titleMedium
                    )
                }
            }
        }

        items(
            items = uiState.notebooks,
            key = { notebook -> notebook.id }
        ) { notebook ->
            GenesysPanel(
                modifier = Modifier.fillMaxWidth(),
                tone = GenesysPanelTone.Raised,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                onClick = {
                    viewModel.openNotebook(notebook.id) { pageId, bookId ->
                        onOpenNotebook(pageId, bookId)
                    }
                }
            ) {
                GenesysText(
                    text = notebook.title,
                    style = GenesysTheme.typography.titleMedium
                )
                GenesysText(
                    text = "${notebook.pageIds.size} page(s)",
                    style = GenesysTheme.typography.bodyMedium,
                    color = GenesysTheme.colors.outline
                )
            }
        }

        items(
            items = uiState.quickPages,
            key = { page -> page.id }
        ) { page ->
            GenesysPanel(
                modifier = Modifier.fillMaxWidth(),
                tone = GenesysPanelTone.Raised,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                onClick = { onOpenNotebook(page.id, null) }
            ) {
                GenesysText(
                    text = "Quick page",
                    style = GenesysTheme.typography.titleMedium
                )
                GenesysText(
                    text = page.id,
                    style = GenesysTheme.typography.bodyMedium,
                    color = GenesysTheme.colors.outline
                )
            }
        }

        if (!uiState.isLoading && uiState.notebooks.isEmpty() && uiState.quickPages.isEmpty()) {
            item(key = "empty") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = GenesysTheme.colors.outline)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GenesysText(
                        text = "No notebooks yet",
                        style = GenesysTheme.typography.titleMedium
                    )
                    GenesysText(
                        text = "Create a notebook or quick page to open the editor with a real page route.",
                        style = GenesysTheme.typography.bodyMedium,
                        color = GenesysTheme.colors.outline
                    )
                }
            }
        }

        item(key = "footer") {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = GenesysTheme.colors.outline)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GenesysText(
                    text = "Baseline ready for porting",
                    style = GenesysTheme.typography.titleMedium
                )
                GenesysText(
                    text = "The library now opens concrete page and book ids. Remaining notable drift is deeper in editor data flow, exports, and page management.",
                    style = GenesysTheme.typography.bodyMedium,
                    color = GenesysTheme.colors.outline
                )
            }
        }
    }
}
