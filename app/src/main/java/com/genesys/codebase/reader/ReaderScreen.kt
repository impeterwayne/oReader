package com.genesys.codebase.reader

import android.app.Activity
import android.content.Context
import android.text.format.DateUtils
import android.text.format.Formatter
import android.widget.Toast
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.genesys.core.designsystem.component.GenesysPanel
import com.genesys.core.designsystem.component.GenesysPanelTone
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysSecondaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.feature.koreader.host.KoreaderActivity
import com.hjq.permissions.dsl.XXPermissionsExt
import com.hjq.permissions.permission.special.ManageExternalStoragePermission
import kotlin.math.roundToInt

@Composable
fun ReaderScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: ReaderLibraryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context.findActivity()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.message) {
        val message = uiState.message ?: return@LaunchedEffect
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        viewModel.consumeMessage()
    }

    ReaderScreen(
        state = uiState,
        onRequestLibraryAccess = {
            activity?.let { requestReaderLibraryAccess(it, viewModel::refreshBooks) }
        },
        onRefresh = viewModel::refreshBooks,
        onOpenBook = { book ->
            val intent = KoreaderActivity.LaunchMode.toIntent(
                context,
                KoreaderActivity.LaunchMode.OpenDocument(book.filePath)
            )
            context.startActivity(intent)
        },
        onRemoveBook = viewModel::removeBook,
        modifier = modifier
    )
}

@Composable
private fun ReaderScreen(
    state: ReaderLibraryUiState,
    onRequestLibraryAccess: () -> Unit,
    onRefresh: () -> Unit,
    onOpenBook: (ReaderBook) -> Unit,
    onRemoveBook: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(GenesysTheme.colors.surfaceDim)
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(
                modifier = Modifier.statusBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
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
                onRequestLibraryAccess = onRequestLibraryAccess,
                onRefresh = onRefresh
            )
        }

        when {
            state.isLoading -> {
                item {
                    ReaderStatusPanel(
                        title = "Loading library",
                        body = "Scanning device books and reading KOReader progress."
                    )
                }
            }

            state.books.isEmpty() && state.storageAccessRequirement != ReaderStorageAccessRequirement.None -> {
                item {
                    ReaderStatusPanel(
                        title = "Library access required",
                        body = "Allow library access so oReader can scan supported books on device storage instead of dropping you into KOReader's file manager."
                    )
                }
            }

            state.books.isEmpty() -> {
                item {
                    ReaderStatusPanel(
                        title = "Library is empty",
                        body = "No supported books were found yet. Add books to device storage or share a book into oReader to keep a managed copy here."
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
    state: ReaderLibraryUiState,
    onRequestLibraryAccess: () -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    GenesysPanel(
        modifier = modifier.fillMaxWidth(),
        tone = GenesysPanelTone.Frame,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        GenesysText(
            text = if (state.books.isEmpty()) {
                "No books available yet"
            } else {
                "${state.books.size} books in your library"
            },
            style = GenesysTheme.typography.titleMedium
        )
        GenesysText(
            text = if (state.storageAccessRequirement == ReaderStorageAccessRequirement.None) {
                "oReader scans supported books directly from device storage and keeps shared or protected files as managed copies in-app."
            } else {
                "Grant library access so oReader can manage books in-app first instead of sending you to KOReader's file manager."
            },
            style = GenesysTheme.typography.bodyMedium,
            color = GenesysTheme.colors.outline
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (state.storageAccessRequirement != ReaderStorageAccessRequirement.None) {
                GenesysPrimaryButton(
                    text = "Allow library access",
                    onClick = onRequestLibraryAccess
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
private fun ReaderStatusPanel(
    title: String,
    body: String,
    modifier: Modifier = Modifier
) {
    GenesysPanel(
        modifier = modifier.fillMaxWidth(),
        tone = GenesysPanelTone.Raised,
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
    val progressLabel = book.percentComplete
        ?.let { progress -> "${(progress * 100f).roundToInt()}% complete" }
    val lastOpenedLabel = book.lastOpenedAt?.let { timestamp ->
        DateUtils.getRelativeTimeSpanString(
            timestamp,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ).toString()
    }
    val originLabel = when (book.source) {
        ReaderBookSource.Device -> "On device"
        ReaderBookSource.ManagedCopy -> "Managed copy"
    }
    val activityLabel = when (book.source) {
        ReaderBookSource.Device -> "Updated $ageLabel"
        ReaderBookSource.ManagedCopy -> "Added $ageLabel"
    }

    GenesysPanel(
        modifier = modifier.fillMaxWidth(),
        tone = if (hasReadingState) GenesysPanelTone.Heavy else GenesysPanelTone.Raised,
        verticalArrangement = Arrangement.spacedBy(14.dp),
        onClick = onOpenBook
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
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

private fun requestReaderLibraryAccess(activity: Activity, onFinished: () -> Unit) {
    XXPermissionsExt.with(activity)
        .permissions(ManageExternalStoragePermission())
        .onResult { _, _, _ ->
            onFinished()
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
