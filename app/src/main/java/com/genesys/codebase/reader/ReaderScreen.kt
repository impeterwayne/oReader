package com.genesys.codebase.reader

import android.net.Uri
import android.provider.DocumentsContract
import android.text.format.DateUtils
import android.text.format.Formatter
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.genesys.core.designsystem.component.GenesysChip
import com.genesys.core.designsystem.component.GenesysDivider
import com.genesys.core.designsystem.component.GenesysPanel
import com.genesys.core.designsystem.component.GenesysPanelTone
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysSecondaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.feature.koreader.host.KoreaderActivity
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.roundToInt

@Composable
fun ReaderScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: ReaderLibraryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val openTreeLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree()
    ) { treeUri ->
        treeUri?.let { uri ->
            viewModel.onAction(ReaderAction.AddLibraryFolder(uri))
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.sideEffects.collectLatest { sideEffect ->
            when (sideEffect) {
                is ReaderSideEffect.OpenBook -> {
                    context.startActivity(
                        KoreaderActivity.LaunchMode.toIntent(
                            context,
                            KoreaderActivity.LaunchMode.OpenDocument(sideEffect.filePath)
                        )
                    )
                }
                is ReaderSideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    ReaderScreen(
        state = uiState,
        onAddLibraryFolder = { openTreeLauncher.launch(null) },
        onRemoveLibraryFolder = { folderId ->
            viewModel.onAction(ReaderAction.RemoveLibraryFolder(folderId))
        },
        onRemoveInvalidFolders = {
            viewModel.onAction(ReaderAction.RemoveInvalidFolders)
        },
        onRefresh = {
            viewModel.onAction(ReaderAction.RefreshLibrary)
        },
        onOpenBook = { book ->
            viewModel.onAction(ReaderAction.OpenBook(book))
        },
        onRemoveBook = { bookId ->
            viewModel.onAction(ReaderAction.RemoveBook(bookId))
        },
        modifier = modifier
    )
}

@Composable
private fun ReaderScreen(
    state: ReaderUiState,
    onAddLibraryFolder: () -> Unit,
    onRemoveLibraryFolder: (String) -> Unit,
    onRemoveInvalidFolders: () -> Unit,
    onRefresh: () -> Unit,
    onOpenBook: (ReaderBook) -> Unit,
    onRemoveBook: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GenesysTheme.colors.surfaceDim)
            .padding(
                horizontal = GenesysTheme.spacing.md + GenesysTheme.spacing.xxs,
                vertical = GenesysTheme.spacing.lg
            ),
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
    ) {
        item {
            Column(
                modifier = Modifier.statusBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
            ) {
                GenesysText(
                    text = "Reader Library",
                    style = GenesysTheme.typography.titleLarge,
                    color = GenesysTheme.colors.primary
                )
            }
        }

        item {
            ReaderLibrarySummaryPanel(
                state = state,
                onAddLibraryFolder = onAddLibraryFolder,
                onRemoveLibraryFolder = onRemoveLibraryFolder,
                onRemoveInvalidFolders = onRemoveInvalidFolders,
                onRefresh = onRefresh
            )
        }

        when {
            state.isLoading -> {
                item {
                    ReaderStatusPanel(
                        title = "Loading library",
                        body = "Scanning selected folders and reading KOReader progress."
                    )
                }
            }
            state.books.isEmpty() && state.selectedFolders.isEmpty() -> {
                item {
                    ReaderStatusPanel(
                        title = "No folders selected",
                        body = "Add one or more folders to scan your books with folder-based access."
                    )
                }
            }
            state.books.isEmpty() -> {
                item {
                    ReaderStatusPanel(
                        title = "No books found",
                        body = "Selected folders are ready, but no supported books were found yet. Managed copies still appear here when imported."
                    )
                }
            }
            else -> {
                items(
                    items = state.books,
                    key = { book -> book.id }
                ) { book ->
                    ReaderBookCard(
                        book = book,
                        onOpenBook = { onOpenBook(book) },
                        onRemoveBook = { onRemoveBook(book.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ReaderLibrarySummaryPanel(
    state: ReaderUiState,
    onAddLibraryFolder: () -> Unit,
    onRemoveLibraryFolder: (String) -> Unit,
    onRemoveInvalidFolders: () -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val validFolderCount = state.selectedFolders.count { it.isValid }

    GenesysPanel(
        modifier = modifier.fillMaxWidth(),
        tone = GenesysPanelTone.Frame,
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
    ) {
        GenesysText(
            text = if (state.books.isEmpty()) {
                "Library waiting for books"
            } else {
                "${state.books.size} books in your library"
            },
            style = GenesysTheme.typography.titleMedium
        )
        GenesysText(
            text = buildString {
                append(validFolderCount)
                append(" active folder")
                if (validFolderCount != 1) {
                    append('s')
                }
                append(" selected for scanning")
                if (state.invalidFolders.isNotEmpty()) {
                    append(". ")
                    append(state.invalidFolders.size)
                    append(" unavailable folder")
                    if (state.invalidFolders.size != 1) {
                        append('s')
                    }
                    append(" can be removed.")
                }
            },
            style = GenesysTheme.typography.bodyMedium,
            color = GenesysTheme.colors.outline
        )

        if (state.selectedFolders.isEmpty()) {
            ReaderStatusPanel(
                title = "No folders yet",
                body = "Add folder to build reader library from selected locations."
            )
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)) {
                state.selectedFolders.forEachIndexed { index, folder ->
                    if (index > 0) {
                        GenesysDivider()
                    }
                    ReaderFolderRow(
                        folder = folder,
                        onRemoveFolder = { onRemoveLibraryFolder(folder.id) }
                    )
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)) {
            GenesysPrimaryButton(
                text = "Add folder",
                onClick = onAddLibraryFolder
            )
            if (state.invalidFolders.isNotEmpty()) {
                GenesysSecondaryButton(
                    text = "Clear invalid",
                    onClick = onRemoveInvalidFolders
                )
            }
            GenesysSecondaryButton(
                text = "Refresh",
                onClick = onRefresh
            )
        }
    }
}

@Composable
private fun ReaderFolderRow(
    folder: ReaderLibraryFolder,
    onRemoveFolder: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isValid = folder.isValid
    val statusLabel = if (isValid) "Selected" else folder.invalidReason ?: "Unavailable"
    val pathPreview = runCatching {
        DocumentsContract.getTreeDocumentId(Uri.parse(folder.treeUri))
            .substringAfter(':', folder.displayName)
    }.getOrDefault(folder.displayName)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xxs)
            ) {
                GenesysText(
                    text = folder.displayName,
                    style = GenesysTheme.typography.titleSmall,
                    color = if (isValid) {
                        GenesysTheme.colors.onSurface
                    } else {
                        GenesysTheme.colors.primary
                    }
                )
                GenesysText(
                    text = pathPreview,
                    style = GenesysTheme.typography.bodySmall,
                    color = GenesysTheme.colors.outline
                )
            }
            GenesysChip(
                text = statusLabel,
                selected = isValid
            )
            GenesysSecondaryButton(
                text = "Remove",
                onClick = onRemoveFolder
            )
        }
    }
}

@Composable
private fun ReaderStatusPanel(
    title: String,
    body: String,
    modifier: Modifier = Modifier
) {
    GenesysPanel(
        modifier = modifier.fillMaxWidth(),
        tone = GenesysPanelTone.Raised,
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
    ) {
        GenesysText(
            text = title,
            style = GenesysTheme.typography.titleMedium
        )
        GenesysText(
            text = body,
            style = GenesysTheme.typography.bodyMedium,
            color = GenesysTheme.colors.outline
        )
    }
}

@Composable
private fun ReaderBookCard(
    book: ReaderBook,
    onOpenBook: () -> Unit,
    onRemoveBook: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val hasReadingState = book.lastOpenedAt != null
    val sizeLabel = Formatter.formatShortFileSize(context, book.fileSizeBytes)
    val ageLabel = DateUtils.getRelativeTimeSpanString(
        book.addedAt,
        System.currentTimeMillis(),
        DateUtils.MINUTE_IN_MILLIS
    ).toString()
    val progressLabel = book.percentComplete?.let { progress ->
        "${(progress * 100f).roundToInt()}% complete"
    }
    val lastOpenedLabel = book.lastOpenedAt?.let { timestamp ->
        DateUtils.getRelativeTimeSpanString(
            timestamp,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ).toString()
    }
    val originLabel = when (book.source) {
        ReaderBookSource.SafFolder -> "Selected folder"
        ReaderBookSource.ManagedCopy -> "Managed copy"
    }
    val activityLabel = when (book.source) {
        ReaderBookSource.SafFolder -> "Updated $ageLabel"
        ReaderBookSource.ManagedCopy -> "Added $ageLabel"
    }

    GenesysPanel(
        modifier = modifier.fillMaxWidth(),
        tone = if (hasReadingState) GenesysPanelTone.Heavy else GenesysPanelTone.Raised,
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm),
        onClick = onOpenBook
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)) {
            GenesysText(
                text = book.title,
                style = GenesysTheme.typography.titleMedium,
                color = if (hasReadingState) {
                    GenesysTheme.colors.onPrimaryContainer
                } else {
                    GenesysTheme.colors.onSurface
                }
            )
            GenesysText(
                text = "${book.extension.uppercase()} | $sizeLabel | $originLabel",
                style = GenesysTheme.typography.bodyMedium,
                color = if (hasReadingState) {
                    GenesysTheme.colors.onPrimaryContainer
                } else {
                    GenesysTheme.colors.outline
                }
            )
            GenesysText(
                text = "$activityLabel | ${book.locationLabel}",
                style = GenesysTheme.typography.bodyMedium,
                color = if (hasReadingState) {
                    GenesysTheme.colors.onPrimaryContainer
                } else {
                    GenesysTheme.colors.outline
                }
            )
            if (lastOpenedLabel != null) {
                GenesysText(
                    text = buildString {
                        append("Last opened ")
                        append(lastOpenedLabel)
                        if (progressLabel != null) {
                            append(" | ")
                            append(progressLabel)
                        }
                    },
                    style = GenesysTheme.typography.bodyMedium,
                    color = GenesysTheme.colors.onPrimaryContainer
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)) {
            GenesysPrimaryButton(
                text = "Open",
                onClick = onOpenBook
            )
            if (book.source == ReaderBookSource.ManagedCopy) {
                GenesysSecondaryButton(
                    text = "Remove",
                    onClick = onRemoveBook
                )
            }
        }
    }
}
