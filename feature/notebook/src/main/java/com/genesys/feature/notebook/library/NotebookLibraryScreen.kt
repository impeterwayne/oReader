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
import com.genesys.core.designsystem.component.GenesysPanel
import com.genesys.core.designsystem.component.GenesysPanelTone
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

private data class NotebookPreview(
    val title: String,
    val details: String
)

private val previews = listOf(
    NotebookPreview(
        title = "Meeting Notes",
        details = "Compose shell is live. Shared data and Room storage land in the next slice."
    ),
    NotebookPreview(
        title = "Quick Pages",
        details = "Library, editor, and reader entry points now share one host application."
    ),
    NotebookPreview(
        title = "Import Queue",
        details = "File provider wiring is in place so later export and share tasks can attach cleanly."
    )
)

@Composable
fun NotebookLibraryRoute(
    onOpenNotebook: () -> Unit,
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
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.statusBarsPadding()
            ) {
                GenesysText(
                    text = "Notebook Library",
                    style = GenesysTheme.typography.titleLarge,
                    color = GenesysTheme.colors.primary
                )
                GenesysText(
                    text = "The launcher now opens a notebook-first shell.",
                    style = GenesysTheme.typography.bodyLarge,
                    color = GenesysTheme.colors.outline
                )
            }
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                GenesysPrimaryButton(
                    text = "Open sample notebook",
                    onClick = onOpenNotebook
                )
            }
        }

        items(previews) { preview ->
            GenesysPanel(
                modifier = Modifier.fillMaxWidth(),
                tone = GenesysPanelTone.Raised,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                GenesysText(
                    text = preview.title,
                    style = GenesysTheme.typography.titleMedium
                )
                GenesysText(
                    text = preview.details,
                    style = GenesysTheme.typography.bodyMedium,
                    color = GenesysTheme.colors.outline
                )
            }
        }

        item {
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
                    text = "Next tasks can move notebook models, repositories, and editor behavior into shared modules without changing the launcher again.",
                    style = GenesysTheme.typography.bodyMedium,
                    color = GenesysTheme.colors.outline
                )
            }
        }
    }
}
