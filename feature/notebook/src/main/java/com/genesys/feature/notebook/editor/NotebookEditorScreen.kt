package com.genesys.feature.notebook.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.feature.notebook.ui.LocalSnackContext
import com.genesys.feature.notebook.ui.SnackState

@Composable
fun NotebookEditorRoute(
    pageId: String?,
    bookId: String?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    goToPages: (String) -> Unit = {},
    goToBugReport: () -> Unit = {}
) {
    val snackState = remember { SnackState() }

    CompositionLocalProvider(LocalSnackContext provides snackState) {
        if (pageId == null) {
            NotebookEditorStatus(
                modifier = modifier,
                title = "Notebook unavailable",
                message = "No notebook page could be opened.",
                buttonLabel = "Back to library",
                onClick = onBack
            )
        } else {
            EditorView(
                initialPageId = pageId,
                bookId = bookId,
                isQuickNavOpen = false,
                onPageChange = {},
                goToLibrary = { onBack() },
                goToPages = goToPages,
                goToBugReport = goToBugReport
            )
        }
    }
}

@Composable
private fun NotebookEditorStatus(
    title: String,
    message: String,
    buttonLabel: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GenesysTheme.colors.surfaceDim)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.statusBarsPadding()
        ) {
            GenesysText(
                text = title,
                style = GenesysTheme.typography.titleLarge
            )
            GenesysText(
                text = message,
                style = GenesysTheme.typography.bodyLarge,
                color = GenesysTheme.colors.outline
            )
        }

        GenesysPrimaryButton(
            text = buttonLabel,
            onClick = onClick,
            modifier = Modifier.height(48.dp)
        )
    }
}
