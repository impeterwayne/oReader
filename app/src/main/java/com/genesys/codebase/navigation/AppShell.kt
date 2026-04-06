package com.genesys.codebase.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.genesys.codebase.reader.ReaderScreenRoute
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.feature.notebook.editor.EditorDestination
import com.genesys.feature.notebook.editor.NotebookEditorRoute
import com.genesys.feature.notebook.library.NotebookLibraryRoute
import com.genesys.feature.notebook.pages.NotebookPagesDestination
import com.genesys.feature.notebook.pages.NotebookPagesRoute

private enum class AppDestination(
    val route: String,
    val label: String,
    val badge: String
) {
    Reader(route = "reader", label = "Reader", badge = "RD"),
    Notebook(route = "notebook-library", label = "Notebook", badge = "NB")
}

private val bottomDestinations = AppDestination.entries

@Composable
fun AppShell(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GenesysTheme.colors.surfaceDim)
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            NavHost(
                navController = navController,
                startDestination = AppDestination.Reader.route,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(AppDestination.Reader.route) {
                    ReaderScreenRoute()
                }
                composable(AppDestination.Notebook.route) {
                    NotebookLibraryRoute(
                        onOpenNotebook = { pageId, bookId ->
                            navController.navigate(EditorDestination.createRoute(pageId, bookId))
                        }
                    )
                }
                composable(
                    route = EditorDestination.routeWithArgs,
                    arguments = listOf(
                        navArgument(EditorDestination.PAGE_ID_ARG) { type = NavType.StringType },
                        navArgument(EditorDestination.BOOK_ID_ARG) {
                            type = NavType.StringType
                            nullable = true
                            defaultValue = null
                        }
                    )
                ) { backStackEntry ->
                    NotebookEditorRoute(
                        pageId = backStackEntry.arguments?.getString(EditorDestination.PAGE_ID_ARG),
                        bookId = backStackEntry.arguments?.getString(EditorDestination.BOOK_ID_ARG),
                        onBack = { navController.popBackStack() },
                        goToPages = { bookId ->
                            navController.navigate(NotebookPagesDestination.createRoute(bookId))
                        }
                    )
                }
                composable(
                    route = NotebookPagesDestination.routeWithArgs,
                    arguments = listOf(
                        navArgument(NotebookPagesDestination.BOOK_ID_ARG) {
                            type = NavType.StringType
                        }
                    )
                ) { backStackEntry ->
                    val bookId =
                        backStackEntry.arguments?.getString(NotebookPagesDestination.BOOK_ID_ARG)
                            ?: return@composable
                    NotebookPagesRoute(
                        bookId = bookId,
                        onBack = { navController.popBackStack() },
                        onOpenPage = { pageId, resolvedBookId ->
                            navController.navigate(
                                EditorDestination.createRoute(pageId, resolvedBookId)
                            )
                        }
                    )
                }
            }
        }

        if (currentDestination?.isTopLevelDestinationAny(bottomDestinations) == true) {
            AppBottomBar(
                currentDestination = currentDestination,
                onDestinationSelected = { destination ->
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun AppBottomBar(
    currentDestination: NavDestination?,
    onDestinationSelected: (AppDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(GenesysTheme.colors.surfaceContainerHighest)
            .border(
                width = GenesysTheme.strokes.thin,
                color = GenesysTheme.colors.outline,
                shape = GenesysTheme.shapes.large
            )
            .navigationBarsPadding()
            .padding(
                horizontal = GenesysTheme.spacing.xs,
                vertical = GenesysTheme.spacing.sm
            ),
        horizontalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xs)
    ) {
        bottomDestinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestination(destination)

            BottomBarItem(
                destination = destination,
                selected = selected,
                onClick = { onDestinationSelected(destination) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    destination: AppDestination,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = if (selected) {
        GenesysTheme.colors.primaryContainer
    } else {
        GenesysTheme.colors.surface
    }
    val contentColor = if (selected) {
        GenesysTheme.colors.onPrimaryContainer
    } else {
        GenesysTheme.colors.onSurface
    }
    val badgeColor = if (selected) {
        GenesysTheme.colors.surface
    } else {
        GenesysTheme.colors.surfaceContainerLow
    }
    val badgeContentColor = GenesysTheme.colors.onSurface

    Column(
        modifier = modifier
            .clip(GenesysTheme.shapes.medium)
            .background(containerColor)
            .border(
                width = GenesysTheme.strokes.thin,
                color = if (selected) GenesysTheme.colors.primary else GenesysTheme.colors.outlineVariant,
                shape = GenesysTheme.shapes.medium
            )
            .clickable(onClick = onClick)
            .padding(
                horizontal = GenesysTheme.spacing.xs,
                vertical = GenesysTheme.spacing.sm
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(GenesysTheme.spacing.xxs)
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(badgeColor)
                .border(
                    width = GenesysTheme.strokes.thin,
                    color = if (selected) GenesysTheme.colors.primary else GenesysTheme.colors.outlineVariant,
                    shape = GenesysTheme.shapes.small
                ),
            contentAlignment = Alignment.Center
        ) {
            GenesysText(
                text = destination.badge,
                style = GenesysTheme.typography.labelSmall,
                color = badgeContentColor
            )
        }

        GenesysText(
            text = destination.label,
            style = GenesysTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
            color = contentColor
        )
    }
}

private fun NavDestination?.isTopLevelDestination(destination: AppDestination): Boolean {
    return this?.hierarchy?.any { navDestination ->
        navDestination.route == destination.route
    } == true
}

private fun NavDestination?.isTopLevelDestinationAny(destinations: List<AppDestination>): Boolean {
    return this?.hierarchy?.any { navDestination ->
        destinations.any { destination ->
            navDestination.route == destination.route
        }
    } == true
}
