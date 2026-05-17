package com.genesys.feature.notebook.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.genesys.core.designsystem.component.GenesysDivider
import com.genesys.core.designsystem.component.GenesysPageFrame
import com.genesys.core.designsystem.component.GenesysPanel
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.core.model.notebook.Notebook
import com.genesys.core.model.notebook.NotebookPage
import com.genesys.core.navigation.Route
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
    onOpenNotebook: (Route.NotebookEditor) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NotebookLibraryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GenesysPageFrame(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(GenesysTheme.colors.surface)
                .statusBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
        ) {
            item(key = "header") {
                NotebookLibraryHeader(
                    title = "Notebook Library",
                    subtitle = "Notebooks and quick pages"
                )
            }

            item(key = "actions") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
                ) {
                    GenesysPanel(
                        modifier = Modifier.weight(1f),
                        tone = com.genesys.core.designsystem.component.GenesysPanelTone.Heavy,
                        onClick = {
                            viewModel.createNotebook { pageId, bookId ->
                                onOpenNotebook(Route.NotebookEditor(pageId = pageId, bookId = bookId))
                            }
                        },
                        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
                    ) {
                        Image(
                            painter = painterResource(id = com.genesys.core.designsystem.R.drawable.ic_notebook),
                            contentDescription = "New notebook",
                            colorFilter = ColorFilter.tint(GenesysTheme.colors.onPrimaryContainer)
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xxs)) {
                            GenesysText(
                                text = "New notebook",
                                style = GenesysTheme.typography.titleMedium,
                                color = GenesysTheme.colors.onPrimaryContainer
                            )
                            GenesysText(
                                text = "Blank notebook",
                                style = GenesysTheme.typography.bodySmall,
                                color = GenesysTheme.colors.onPrimaryContainer.copy(alpha = 0.8f)
                            )
                        }
                    }

                    GenesysPanel(
                        modifier = Modifier.weight(1f),
                        tone = com.genesys.core.designsystem.component.GenesysPanelTone.Raised,
                        onClick = {
                            viewModel.createQuickPage { pageId ->
                                onOpenNotebook(Route.NotebookEditor(pageId = pageId))
                            }
                        },
                        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
                    ) {
                        Image(
                            painter = painterResource(id = com.genesys.core.designsystem.R.drawable.ic_book_open),
                            contentDescription = "Quick page",
                            colorFilter = ColorFilter.tint(GenesysTheme.colors.primary)
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xxs)) {
                            GenesysText(
                                text = "Quick page",
                                style = GenesysTheme.typography.titleMedium,
                                color = GenesysTheme.colors.onSurface
                            )
                            GenesysText(
                                text = "Standalone note",
                                style = GenesysTheme.typography.bodySmall,
                                color = GenesysTheme.colors.outline
                            )
                        }
                    }
                }
            }

            when {
                uiState.isLoading -> {
                    item(key = "loading") {
                        NotebookLibraryStatusPanel(
                            title = "Loading notebooks",
                            body = "Gathering saved notebooks and standalone pages from local storage."
                        )
                    }
                }

                uiState.notebooks.isEmpty() && uiState.quickPages.isEmpty() -> {
                    item(key = "empty") {
                        NotebookLibraryStatusPanel(
                            title = "No notebooks yet",
                            body = "Create a notebook or quick page to open the editor with a real page route."
                        )
                    }
                }

                else -> {
                    if (uiState.notebooks.isNotEmpty()) {
                        item(key = "notebooks-header") {
                            NotebookLibraryGroupHeader(
                                title = "Notebooks",
                                count = uiState.notebooks.size
                            )
                        }
                        items(
                            items = uiState.notebooks,
                            key = { notebook -> notebook.id }
                        ) { notebook ->
                            NotebookRow(
                                title = notebook.title,
                                meta = "${notebook.pageIds.size} page(s)",
                                onClick = {
                                    viewModel.openNotebook(notebook.id) { pageId, bookId ->
                                        onOpenNotebook(Route.NotebookEditor(pageId = pageId, bookId = bookId))
                                    }
                                }
                            )
                            GenesysDivider()
                        }
                    }

                    if (uiState.quickPages.isNotEmpty()) {
                        item(key = "quick-pages-header") {
                            NotebookLibraryGroupHeader(
                                title = "Quick pages",
                                count = uiState.quickPages.size
                            )
                        }
                        items(
                            items = uiState.quickPages,
                            key = { page -> page.id }
                        ) { page ->
                            NotebookRow(
                                title = "Quick page",
                                meta = page.id,
                                onClick = { onOpenNotebook(Route.NotebookEditor(pageId = page.id)) }
                            )
                            GenesysDivider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NotebookLibraryHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = GenesysTheme.strokes.thin,
                color = GenesysTheme.colors.outlineVariant,
                shape = GenesysTheme.shapes.small
            )
            .padding(GenesysTheme.spacing.sm),
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
    ) {
        GenesysText(
            text = title,
            style = GenesysTheme.typography.titleLarge,
            color = GenesysTheme.colors.onSurface
        )
        GenesysText(
            text = subtitle,
            style = GenesysTheme.typography.bodySmall,
            color = GenesysTheme.colors.outline
        )
        GenesysDivider()
        GenesysText(
            text = "Open a notebook at its current page or jump into a standalone quick page.",
            style = GenesysTheme.typography.bodySmall,
            color = GenesysTheme.colors.outline
        )
    }
}

@Composable
private fun NotebookLibraryGroupHeader(
    title: String,
    count: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GenesysText(
                text = title,
                style = GenesysTheme.typography.titleMedium,
                color = GenesysTheme.colors.onSurface
            )
            GenesysText(
                text = count.toString(),
                style = GenesysTheme.typography.labelMedium,
                color = GenesysTheme.colors.outline
            )
        }
        GenesysDivider()
    }
}

@Composable
private fun NotebookLibraryStatusPanel(
    title: String,
    body: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = GenesysTheme.strokes.thin,
                color = GenesysTheme.colors.outlineVariant,
                shape = GenesysTheme.shapes.small
            )
            .padding(GenesysTheme.spacing.sm),
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
    ) {
        GenesysText(
            text = title,
            style = GenesysTheme.typography.titleMedium,
            color = GenesysTheme.colors.onSurface
        )
        GenesysText(
            text = body,
            style = GenesysTheme.typography.bodySmall,
            color = GenesysTheme.colors.outline
        )
    }
}

@Composable
private fun NotebookRow(
    title: String,
    meta: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(GenesysTheme.shapes.small)
            .clickable(onClick = onClick)
            .padding(vertical = GenesysTheme.spacing.xs),
        horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xxs)
        ) {
            GenesysText(
                text = title,
                style = GenesysTheme.typography.titleMedium,
                color = GenesysTheme.colors.onSurface
            )
            GenesysText(
                text = meta,
                style = GenesysTheme.typography.bodySmall,
                color = GenesysTheme.colors.outline
            )
        }
        Box(
            modifier = Modifier
                .clip(GenesysTheme.shapes.small)
                .border(
                    width = GenesysTheme.strokes.thin,
                    color = GenesysTheme.colors.outlineVariant,
                    shape = GenesysTheme.shapes.small
                )
                .clickable(onClick = onClick)
                .padding(GenesysTheme.spacing.xs)
        ) {
            Image(
                painter = painterResource(id = com.genesys.core.designsystem.R.drawable.ic_chevron_right),
                contentDescription = "Open",
                colorFilter = ColorFilter.tint(GenesysTheme.colors.onSurface)
            )
        }
    }
}
