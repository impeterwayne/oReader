package com.genesys.feature.notebook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import com.genesys.feature.notebook.editor.NotebookEditorRoute
import com.genesys.feature.notebook.library.NotebookLibraryRoute
import com.genesys.feature.notebook.pages.NotebookPagesRoute

@Composable
fun NotebookGraph(
    currentRoute: NavKey?,
    onOpenNotebook: (NotebookEditor) -> Unit,
    onOpenPageList: (bookId: String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (val route = currentRoute) {
        is NotebookEditor -> {
            NotebookEditorRoute(
                route = route,
                onBack = onBack,
                modifier = modifier,
                goToPages = onOpenPageList
            )
        }

        is NotebookPages -> {
            NotebookPagesRoute(
                route = route,
                onBack = onBack,
                onOpenPage = { pageId, bookId ->
                    onOpenNotebook(NotebookEditor(pageId = pageId, bookId = bookId))
                },
                modifier = modifier
            )
        }

        else -> {
            NotebookLibraryRoute(
                onOpenNotebook = onOpenNotebook,
                modifier = modifier
            )
        }
    }
}
