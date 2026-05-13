package com.genesys.codebase.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.rememberNavBackStack
import com.genesys.core.designsystem.theme.GenesysTheme
import com.genesys.core.navigation.AppNavigator
import com.genesys.core.navigation.AppNavigatorImpl
import com.genesys.feature.library.navigation.LibraryGraph
import com.genesys.feature.library.navigation.LibrarySettings
import com.genesys.feature.notebook.navigation.NotebookGraph
import com.genesys.feature.notebook.navigation.NotebookPages

@Composable
fun rememberAppState(
    initialDestination: TopLevelDestination = TopLevelDestination.Reader
): AppState {
    val currentDestinationState = rememberSaveable { mutableStateOf(initialDestination) }

    val backStacks = TopLevelDestination.entries.associateWith { destination ->
        rememberNavBackStack(destination.route)
    }

    val navigators = TopLevelDestination.entries.associateWith { destination ->
        val backStack = backStacks.getValue(destination)
        remember(backStack) { AppNavigatorImpl(backStack) }
    }

    return remember(backStacks, navigators) {
        AppState(
            currentDestinationState = currentDestinationState,
            backStacks = backStacks,
            navigators = navigators
        )
    }
}

class AppState(
    val currentDestinationState: MutableState<TopLevelDestination>,
    val backStacks: Map<TopLevelDestination, NavBackStack>,
    val navigators: Map<TopLevelDestination, AppNavigator>
) {
    var currentDestination: TopLevelDestination
        get() = currentDestinationState.value
        set(value) {
            currentDestinationState.value = value
        }

    val activeBackStack: NavBackStack
        get() = backStacks.getValue(currentDestination)

    val activeNavigator: AppNavigator
        get() = navigators.getValue(currentDestination)

    val showBottomBar: Boolean
        get() = !activeNavigator.canPop

    fun handleBack() {
        if (!activeNavigator.popIfPossible()) {
            if (currentDestination != TopLevelDestination.Reader) {
                currentDestination = TopLevelDestination.Reader
            }
        }
    }

    fun selectDestination(destination: TopLevelDestination) {
        if (currentDestination == destination) {
            activeNavigator.popToRoot()
        } else {
            currentDestination = destination
        }
    }
}

@Composable
fun NavHost(
    modifier: Modifier = Modifier
) {
    val appState = rememberAppState()

    BackHandler(enabled = appState.activeNavigator.canPop || appState.currentDestination != TopLevelDestination.Reader) {
        appState.handleBack()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GenesysTheme.colors.surfaceDim)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            when (appState.currentDestination) {
                TopLevelDestination.Reader -> {
                    LibraryGraph(
                        currentRoute = appState.activeBackStack.lastOrNull(),
                        onOpenSettings = {
                            appState.activeNavigator.navigate(LibrarySettings)
                        },
                        onBack = appState::handleBack,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                TopLevelDestination.Notebook -> {
                    NotebookGraph(
                        currentRoute = appState.activeBackStack.lastOrNull(),
                        onOpenNotebook = appState.activeNavigator::navigate,
                        onOpenPageList = { bookId ->
                            appState.activeNavigator.navigate(NotebookPages(bookId = bookId))
                        },
                        onBack = appState::handleBack,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        if (appState.showBottomBar) {
            AppBottomBar(
                currentDestination = appState.currentDestination,
                onDestinationSelected = appState::selectDestination
            )
        }
    }
}
