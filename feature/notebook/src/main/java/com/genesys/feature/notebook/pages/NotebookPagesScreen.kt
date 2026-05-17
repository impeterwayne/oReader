package com.genesys.feature.notebook.pages

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.core.model.notebook.Notebook
import com.genesys.core.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

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
    route: Route.NotebookPages,
    onBack: () -> Unit,
    onOpenPage: (pageId: String, bookId: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NotebookPagesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(route.bookId) {
        viewModel.load(route.bookId)
    }

    GenesysPageFrame(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(GenesysTheme.colors.surface)
                .statusBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
        ) {
            item(key = "header") {
                NotebookPagesHeader(
                    title = uiState.notebook?.title ?: "Pages",
                    subtitle = "Notebook page list",
                    onBack = onBack
                )
            }

            when {
                uiState.isLoading -> {
                    item(key = "loading") {
                        NotebookPagesStatusPanel(
                            title = "Loading pages",
                            body = "Fetching the current notebook and its saved page order."
                        )
                    }
                }

                uiState.notebook == null -> {
                    item(key = "missing") {
                        NotebookPagesStatusPanel(
                            title = "Notebook unavailable",
                            body = "This notebook could not be found. Return to the notebook library and open another one."
                        )
                    }
                }

                else -> {
                    val notebook = requireNotNull(uiState.notebook)
                    item(key = "summary") {
                        NotebookPagesSummary(
                            pageCount = notebook.pageIds.size,
                            currentPageIndex = notebook.pageIds.indexOf(notebook.openPageId)
                        )
                    }
                    itemsIndexed(
                        items = notebook.pageIds,
                        key = { _, pageId -> pageId }
                    ) { index, pageId ->
                        val isOpen = pageId == notebook.openPageId
                        NotebookPageRow(
                            index = index,
                            pageId = pageId,
                            isOpen = isOpen,
                            onClick = { onOpenPage(pageId, notebook.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NotebookPagesHeader(
    title: String,
    subtitle: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = GenesysTheme.strokes.thin,
                color = GenesysTheme.colors.outlineVariant,
                shape = GenesysTheme.shapes.small
            )
            .padding(GenesysTheme.spacing.sm),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xxs)
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
        }
        Box(
            modifier = Modifier
                .clip(GenesysTheme.shapes.small)
                .border(
                    width = GenesysTheme.strokes.thin,
                    color = GenesysTheme.colors.outlineVariant,
                    shape = GenesysTheme.shapes.small
                )
                .clickable(onClick = onBack)
                .padding(GenesysTheme.spacing.xs)
        ) {
            Image(
                painter = painterResource(id = com.genesys.core.designsystem.R.drawable.ic_chevron_left),
                contentDescription = "Back",
                colorFilter = ColorFilter.tint(GenesysTheme.colors.onSurface)
            )
        }
    }
}

@Composable
private fun NotebookPagesSummary(
    pageCount: Int,
    currentPageIndex: Int,
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
            text = if (pageCount == 1) "1 saved page" else "$pageCount saved pages",
            style = GenesysTheme.typography.titleMedium,
            color = GenesysTheme.colors.onSurface
        )
        GenesysText(
            text = if (currentPageIndex >= 0) {
                "Current page is Page ${currentPageIndex + 1}."
            } else {
                "Select any page to reopen the notebook in the editor."
            },
            style = GenesysTheme.typography.bodySmall,
            color = GenesysTheme.colors.outline
        )
    }
}

@Composable
private fun NotebookPagesStatusPanel(
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
private fun NotebookPageRow(
    index: Int,
    pageId: String,
    isOpen: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(GenesysTheme.shapes.small)
            .clickable(onClick = onClick)
            .padding(vertical = GenesysTheme.spacing.xs),
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xxs)
            ) {
                GenesysText(
                    text = "Page ${index + 1}",
                    style = GenesysTheme.typography.titleMedium,
                    color = GenesysTheme.colors.onSurface
                )
                GenesysText(
                    text = if (isOpen) "Current page" else pageId,
                    style = GenesysTheme.typography.bodySmall,
                    color = if (isOpen) GenesysTheme.colors.onSurface else GenesysTheme.colors.outline
                )
            }
            Box(
                modifier = Modifier
                    .clip(GenesysTheme.shapes.small)
                    .border(
                        width = GenesysTheme.strokes.thin,
                        color = if (isOpen) GenesysTheme.colors.outline else GenesysTheme.colors.outlineVariant,
                        shape = GenesysTheme.shapes.small
                    )
                    .clickable(onClick = onClick)
                    .padding(GenesysTheme.spacing.xs)
            ) {
                Image(
                    painter = painterResource(id = com.genesys.core.designsystem.R.drawable.ic_chevron_right),
                    contentDescription = "Open page",
                    colorFilter = ColorFilter.tint(GenesysTheme.colors.onSurface)
                )
            }
        }
        if (isOpen) {
            GenesysText(
                text = "Opens the notebook editor at the current page.",
                style = GenesysTheme.typography.labelMedium,
                color = GenesysTheme.colors.outline
            )
        }
        GenesysDivider()
    }
}
