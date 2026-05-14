package com.genesys.feature.library

import android.app.Activity
import android.content.Context
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
import com.genesys.core.designsystem.component.GenesysPageFrame
import com.genesys.core.designsystem.component.GenesysPanel
import com.genesys.core.designsystem.component.GenesysPanelTone
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysSectionHeader
import com.genesys.core.designsystem.component.GenesysSecondaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.core.model.library.Book
import com.genesys.core.model.library.BookSource
import com.genesys.core.model.library.LibraryFolder
import com.genesys.feature.koreader.host.KoreaderActivity
import com.hjq.permissions.dsl.XXPermissionsExt
import com.hjq.permissions.permission.special.ManageExternalStoragePermission
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.roundToInt

@Composable
fun LibraryScreenRoute(
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.sideEffects.collectLatest { sideEffect ->
            when (sideEffect) {
                is LibrarySideEffect.OpenBook -> {
                    context.startActivity(
                        KoreaderActivity.LaunchMode.toIntent(
                            context,
                            KoreaderActivity.LaunchMode.OpenDocument(sideEffect.filePath)
                        )
                    )
                }
                is LibrarySideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LibraryScreen(
        state = uiState,
        onOpenSettings = onOpenSettings,
        onRefresh = {
            viewModel.onAction(LibraryAction.RefreshLibrary)
        },
        onSelectPage = { pageIndex ->
            viewModel.onAction(LibraryAction.SelectPage(pageIndex))
        },
        onOpenBook = { book ->
            viewModel.onAction(LibraryAction.OpenBook(book))
        },
        onRemoveBook = { bookId ->
            viewModel.onAction(LibraryAction.RemoveBook(bookId))
        },
        modifier = modifier
    )
}

@Composable
fun LibrarySettingsRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context.findActivity()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val openTreeLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree()
    ) { treeUri ->
        treeUri?.let { uri ->
            viewModel.onAction(LibraryAction.AddLibraryFolder(uri))
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.sideEffects.collectLatest { sideEffect ->
            when (sideEffect) {
                is LibrarySideEffect.OpenBook -> Unit
                is LibrarySideEffect.ShowMessage -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LibrarySettingsScreen(
        state = uiState,
        onBack = onBack,
        onAddLibraryFolder = {
            activity?.let {
                requestLibraryFolderAccess(
                    activity = it,
                    onGranted = { openTreeLauncher.launch(null) },
                    onDenied = {
                        Toast.makeText(
                            context,
                            "Storage permission is required before picking a folder.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        },
        onRemoveLibraryFolder = { folderId ->
            viewModel.onAction(LibraryAction.RemoveLibraryFolder(folderId))
        },
        onRemoveInvalidFolders = {
            viewModel.onAction(LibraryAction.RemoveInvalidFolders)
        },
        onRefresh = {
            viewModel.onAction(LibraryAction.RefreshLibrary)
        },
        modifier = modifier
    )
}

@Composable
private fun LibraryScreen(
    state: LibraryUiState,
    onOpenSettings: () -> Unit,
    onRefresh: () -> Unit,
    onSelectPage: (Int) -> Unit,
    onOpenBook: (Book) -> Unit,
    onRemoveBook: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    GenesysPageFrame(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GenesysTheme.colors.surface)
                .statusBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
        ) {
            LibraryHeader(
                title = "Reader Library",
                subtitle = "Paginated shelf",
                primaryActionLabel = "Settings",
                onPrimaryAction = onOpenSettings,
                secondaryActionLabel = "Refresh",
                onSecondaryAction = onRefresh
            )

            LibraryOverviewPanel(
                state = state,
                onOpenSettings = onOpenSettings
            )

            when {
                state.isLoading -> {
                    LibraryStatusPanel(
                        title = "Loading library",
                        body = "Scanning selected folders and reading KOReader progress."
                    )
                }

                state.books.isEmpty() && state.selectedFolders.isEmpty() -> {
                    LibraryStatusPanel(
                        title = "No folders selected",
                        body = "Open settings to add one or more folders before scanning your books."
                    )
                }

                state.books.isEmpty() -> {
                    LibraryStatusPanel(
                        title = "No books found",
                        body = "Selected folders are ready, but no supported books were found yet. Managed copies still appear here when imported."
                    )
                }

                else -> {
                    LibraryPaginationPanel(
                        state = state,
                        onSelectPage = onSelectPage
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
                    ) {
                        state.currentPageBooks.forEach { book ->
                            BookCard(
                                book = book,
                                onOpenBook = { onOpenBook(book) },
                                onRemoveBook = { onRemoveBook(book.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LibrarySettingsScreen(
    state: LibraryUiState,
    onBack: () -> Unit,
    onAddLibraryFolder: () -> Unit,
    onRemoveLibraryFolder: (String) -> Unit,
    onRemoveInvalidFolders: () -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    GenesysPageFrame(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(GenesysTheme.colors.surface)
                .statusBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
        ) {
            item {
                LibraryHeader(
                    title = "Library Settings",
                    subtitle = "Folder management",
                    primaryActionLabel = "Back",
                    onPrimaryAction = onBack,
                    secondaryActionLabel = "Refresh",
                    onSecondaryAction = onRefresh
                )
            }

            item {
                LibrarySummaryPanel(
                    state = state,
                    onAddLibraryFolder = onAddLibraryFolder,
                    onRemoveLibraryFolder = onRemoveLibraryFolder,
                    onRemoveInvalidFolders = onRemoveInvalidFolders,
                    onRefresh = onRefresh
                )
            }
        }
    }
}

@Composable
private fun LibraryHeader(
    title: String,
    subtitle: String,
    primaryActionLabel: String,
    onPrimaryAction: () -> Unit,
    secondaryActionLabel: String,
    onSecondaryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.md)
    ) {
        GenesysSectionHeader(
            title = title,
            subtitle = subtitle
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
        ) {
            GenesysPrimaryButton(
                text = primaryActionLabel,
                onClick = onPrimaryAction
            )
            GenesysSecondaryButton(
                text = secondaryActionLabel,
                onClick = onSecondaryAction
            )
        }
    }
}

@Composable
private fun LibraryOverviewPanel(
    state: LibraryUiState,
    onOpenSettings: () -> Unit,
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
                "${state.books.size} books across ${state.totalPages} pages"
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
                    append(" need attention in settings.")
                }
            },
            style = GenesysTheme.typography.bodyMedium,
            color = GenesysTheme.colors.outline
        )
        if (state.totalPages > 0) {
            GenesysChip(
                text = "Page ${state.selectedPageIndex + 1} of ${state.totalPages}",
                selected = true
            )
        }
        GenesysSecondaryButton(
            text = "Manage folders",
            onClick = onOpenSettings
        )
    }
}

@Composable
private fun LibraryPaginationPanel(
    state: LibraryUiState,
    onSelectPage: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    GenesysPanel(
        modifier = modifier.fillMaxWidth(),
        tone = GenesysPanelTone.Frame,
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
    ) {
        GenesysText(
            text = "Browse by page",
            style = GenesysTheme.typography.titleMedium
        )
        GenesysText(
            text = buildString {
                append("Showing books ")
                append(state.selectedPageIndex * state.pageSize + 1)
                append("–")
                append((state.selectedPageIndex * state.pageSize + state.currentPageBooks.size).coerceAtLeast(0))
                append(" of ")
                append(state.books.size)
            },
            style = GenesysTheme.typography.bodyMedium,
            color = GenesysTheme.colors.outline
        )
        Column(verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)) {
            val pageIndices = 0 until state.totalPages
            pageIndices.chunked(3).forEach { rowPages ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
                ) {
                    rowPages.forEach { pageIndex ->
                        val start = pageIndex * state.pageSize + 1
                        val end = ((pageIndex + 1) * state.pageSize).coerceAtMost(state.books.size)
                        if (pageIndex == state.selectedPageIndex) {
                            GenesysPrimaryButton(
                                text = "$start-$end",
                                onClick = { onSelectPage(pageIndex) },
                                modifier = Modifier.weight(1f)
                            )
                        } else {
                            GenesysSecondaryButton(
                                text = "$start-$end",
                                onClick = { onSelectPage(pageIndex) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LibrarySummaryPanel(
    state: LibraryUiState,
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
            LibraryStatusPanel(
                title = "No folders yet",
                body = "Add folder to build reader library from selected locations."
            )
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)) {
                state.selectedFolders.forEachIndexed { index, folder ->
                    if (index > 0) {
                        GenesysDivider()
                    }
                    FolderRow(
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
private fun FolderRow(
    folder: LibraryFolder,
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
private fun LibraryStatusPanel(
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
private fun BookCard(
    book: Book,
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
        BookSource.SafFolder -> "Selected folder"
        BookSource.ManagedCopy -> "Managed copy"
    }
    val activityLabel = when (book.source) {
        BookSource.SafFolder -> "Updated $ageLabel"
        BookSource.ManagedCopy -> "Added $ageLabel"
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
            if (book.source == BookSource.ManagedCopy) {
                GenesysSecondaryButton(
                    text = "Remove",
                    onClick = onRemoveBook
                )
            }
        }
    }
}

private fun requestLibraryFolderAccess(
    activity: Activity,
    onGranted: () -> Unit,
    onDenied: () -> Unit
) {
    XXPermissionsExt.with(activity)
        .permissions(ManageExternalStoragePermission())
        .onResult { allGranted, _, _ ->
            if (allGranted) {
                onGranted()
            } else {
                onDenied()
            }
        }
        .request()
}

private fun Context.findActivity(): Activity? {
    var current = this
    while (current is android.content.ContextWrapper) {
        if (current is Activity) {
            return current
        }
        current = current.baseContext
    }
    return null
}
