package com.genesys.feature.library

import android.app.Activity
import android.content.Context
import android.text.format.DateUtils
import android.text.format.Formatter
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.genesys.core.designsystem.component.GenesysDivider
import com.genesys.core.designsystem.component.GenesysPageFrame
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysSecondaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.core.model.library.Book
import com.genesys.core.model.library.BookSource
import org.koreader.launcher.KoReaderActivity as KoreaderMainActivity
import com.hjq.permissions.dsl.XXPermissionsExt
import com.hjq.permissions.permission.special.ManageExternalStoragePermission
import org.orbitmvi.orbit.compose.collectSideEffect
import kotlin.math.roundToInt

@Composable
fun LibraryScreenRoute(
    modifier: Modifier = Modifier,
    onOpenSettings: () -> Unit,
    viewModel: LibraryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context.findActivity()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is LibrarySideEffect.OpenBook -> {
                context.startActivity(
                    KoreaderMainActivity.openDocumentIntent(
                        context,
                        sideEffect.filePath
                    )
                )
            }
            is LibrarySideEffect.ShowMessage -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    LibraryScreen(
        state = uiState,
        onOpenFileManager = {
            activity?.let {
                requestLibraryFolderAccess(
                    activity = it,
                    onGranted = {
                        context.startActivity(KoreaderMainActivity.fileManagerIntent(context))
                    },
                    onDenied = {
                        Toast.makeText(
                            context,
                            "Storage permission is required before opening the file manager.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        },
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
        onOpenSettings = onOpenSettings,
        modifier = modifier
    )
}

@Composable
private fun LibraryScreen(
    state: LibraryUiState,
    onOpenFileManager: () -> Unit,
    onRefresh: () -> Unit,
    onSelectPage: (Int) -> Unit,
    onOpenBook: (Book) -> Unit,
    onRemoveBook: (String) -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    GenesysPageFrame(
        modifier = modifier,
        contentPadding = PaddingValues(GenesysTheme.spacing.sm)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GenesysTheme.colors.surface)
                .statusBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
        ) {
            LibraryHeader(
                title = "Reader Library",
                actionIconRes = com.genesys.core.designsystem.R.drawable.ic_settings,
                actionContentDescription = stringResource(id = R.string.settings),
                onAction = onOpenSettings
            )

            LibraryMetaRow(
                primaryText = if (state.books.isEmpty()) {
                    "No books indexed"
                } else {
                    "${state.books.size} books"
                },
                secondaryText = buildString {
                    append("KOReader library")
                    if (state.totalPages > 0) {
                        append(" · Page ")
                        append(state.selectedPageIndex + 1)
                        append('/')
                        append(state.totalPages)
                    }
                }
            )

            GenesysPrimaryButton(
                text = "Open KOReader file manager",
                onClick = onOpenFileManager
            )

            if (state.totalPages > 1) {
                LibraryPaginationRow(
                    state = state,
                    onSelectPage = onSelectPage
                )
            }

            when {
                state.isLoading -> {
                    LibraryStatusPanel(
                        title = "Loading library",
                        body = "Refreshing your KOReader library and reading progress."
                    )
                }

                state.books.isEmpty() && state.selectedFolders.isEmpty() -> {
                    LibraryStatusPanel(
                        title = "Library is empty",
                        body = "Open the KOReader file manager to choose books and build your library."
                    )
                }

                state.books.isEmpty() -> {
                    LibraryStatusPanel(
                        title = "No books found",
                        body = "No supported books were found yet. Open the KOReader file manager to browse and add readable content."
                    )
                }

                else -> {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
                    ) {
                        val chunks = state.currentPageBooks.chunked(2)
                        for (i in 0 until 2) {
                            val rowBooks = chunks.getOrNull(i) ?: emptyList()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
                            ) {
                                rowBooks.forEach { book ->
                                    Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                                        BookCard(
                                            book = book,
                                            onOpenBook = { onOpenBook(book) },
                                            onRemoveBook = { onRemoveBook(book.id) },
                                            modifier = Modifier.fillMaxHeight()
                                        )
                                    }
                                }
                                repeat(2 - rowBooks.size) {
                                    Box(modifier = Modifier.weight(1f).fillMaxHeight())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LibraryHeader(
    title: String,
    actionIconRes: Int,
    actionContentDescription: String,
    onAction: () -> Unit,
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
            .padding(vertical = GenesysTheme.spacing.sm, horizontal = GenesysTheme.spacing.sm),
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
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
                    text = title,
                    style = GenesysTheme.typography.titleLarge,
                    color = GenesysTheme.colors.onSurface
                )
            }
            HeaderActionButton(
                iconRes = actionIconRes,
                contentDescription = actionContentDescription,
                onClick = onAction
            )
        }
    }
}

@Composable
private fun HeaderActionButton(
    iconRes: Int,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
            .clip(GenesysTheme.shapes.small)
            .border(
                width = GenesysTheme.strokes.thin,
                color = GenesysTheme.colors.outlineVariant,
                shape = GenesysTheme.shapes.small
            )
            .clickable(enabled = enabled, onClick = onClick)
            .padding(GenesysTheme.spacing.xs)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(
                if (enabled) GenesysTheme.colors.onSurface else GenesysTheme.colors.outlineVariant
            )
        )
    }
}

@Composable
private fun LibraryMetaRow(
    primaryText: String,
    secondaryText: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = GenesysTheme.spacing.xxs),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GenesysText(
            text = primaryText,
            style = GenesysTheme.typography.bodyMedium,
            color = GenesysTheme.colors.onSurface
        )
        GenesysText(
            text = secondaryText,
            style = GenesysTheme.typography.bodySmall,
            color = GenesysTheme.colors.outline
        )
    }
}

@Composable
private fun LibraryPaginationRow(
    state: LibraryUiState,
    onSelectPage: (Int) -> Unit,
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
            .padding(horizontal = GenesysTheme.spacing.sm, vertical = GenesysTheme.spacing.xs),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeaderActionButton(
            iconRes = com.genesys.core.designsystem.R.drawable.ic_chevron_left,
            contentDescription = "Previous Page",
            onClick = { onSelectPage(state.selectedPageIndex - 1) },
            enabled = state.selectedPageIndex > 0
        )

        GenesysText(
            text = buildString {
                append("Showing ")
                append(state.selectedPageIndex * state.pageSize + 1)
                append("–")
                append((state.selectedPageIndex * state.pageSize + state.currentPageBooks.size).coerceAtLeast(0))
                append(" of ")
                append(state.books.size)
            },
            style = GenesysTheme.typography.bodySmall,
            color = GenesysTheme.colors.outline
        )

        HeaderActionButton(
            iconRes = com.genesys.core.designsystem.R.drawable.ic_chevron_right,
            contentDescription = "Next Page",
            onClick = { onSelectPage(state.selectedPageIndex + 1) },
            enabled = state.selectedPageIndex < state.totalPages - 1
        )
    }
}

@Composable
private fun LibraryStatusPanel(
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

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(GenesysTheme.shapes.small)
            .background(
                if (hasReadingState) {
                    GenesysTheme.colors.surfaceContainerLow
                } else {
                    GenesysTheme.colors.surface
                }
            )
            .border(
                width = GenesysTheme.strokes.thin,
                color = if (hasReadingState) {
                    GenesysTheme.colors.outline
                } else {
                    GenesysTheme.colors.outlineVariant
                },
                shape = GenesysTheme.shapes.small
            )
            .clickable(onClick = onOpenBook)
            .padding(GenesysTheme.spacing.sm),
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.sm)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
        ) {
            GenesysText(
                text = book.title,
                style = GenesysTheme.typography.titleMedium,
                color = GenesysTheme.colors.onSurface
            )
            GenesysText(
                text = "${book.extension.uppercase()} · $sizeLabel · $originLabel",
                style = GenesysTheme.typography.bodySmall,
                color = GenesysTheme.colors.outline
            )
            GenesysText(
                text = "$activityLabel · ${book.locationLabel}",
                style = GenesysTheme.typography.bodySmall,
                color = GenesysTheme.colors.outline
            )
            if (lastOpenedLabel != null) {
                GenesysText(
                    text = buildString {
                        append("Last opened ")
                        append(lastOpenedLabel)
                        if (progressLabel != null) {
                            append(" · ")
                            append(progressLabel)
                        }
                    },
                    style = GenesysTheme.typography.bodySmall,
                    color = GenesysTheme.colors.onSurface
                )
            }
        }

        GenesysDivider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GenesysText(
                text = if (hasReadingState) "Continue reading" else "Open book",
                style = GenesysTheme.typography.labelMedium,
                color = GenesysTheme.colors.onSurface
            )
            GenesysSecondaryButton(
                text = "Remove",
                onClick = onRemoveBook
            )
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
