package com.genesys.feature.notebook.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.genesys.core.navigation.AppNavigator
import com.genesys.core.navigation.Route
import com.genesys.feature.notebook.editor.NotebookEditorRoute
import com.genesys.feature.notebook.library.NotebookLibraryRoute
import com.genesys.feature.notebook.pages.NotebookPagesRoute
import androidx.compose.animation.togetherWith

@Composable
fun NotebookGraph(
    backStack: NavBackStack<NavKey>,
    navigator: AppNavigator,
    modifier: Modifier = Modifier
) {
    val entries = entryProvider<NavKey> {
        entry<Route.Notebook> {
            NotebookLibraryRoute(
                onOpenNotebook = navigator::navigate,
                modifier = modifier
            )
        }

        entry<Route.NotebookEditor> { route ->
            NotebookEditorRoute(
                route = route,
                onBack = navigator::popIfPossible,
                modifier = modifier,
                goToPages = { bookId ->
                    navigator.navigate(Route.NotebookPages(bookId = bookId))
                }
            )
        }

        entry<Route.NotebookPages> { route ->
            NotebookPagesRoute(
                route = route,
                onBack = navigator::popIfPossible,
                onOpenPage = { pageId, bookId ->
                    navigator.navigate(Route.NotebookEditor(pageId = pageId, bookId = bookId))
                },
                modifier = modifier
            )
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = navigator::popIfPossible,
        entryProvider = entries,
        modifier = modifier,
        transitionSpec = { EnterTransition.None togetherWith ExitTransition.None },
        popTransitionSpec = { EnterTransition.None togetherWith ExitTransition.None },
        predictivePopTransitionSpec = { EnterTransition.None togetherWith ExitTransition.None }
    )
}
