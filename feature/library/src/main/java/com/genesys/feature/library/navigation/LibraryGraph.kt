package com.genesys.feature.library.navigation

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
import com.genesys.feature.library.LibraryScreenRoute
import androidx.compose.animation.togetherWith

@Composable
fun LibraryGraph(
    backStack: NavBackStack<NavKey>,
    navigator: AppNavigator,
    modifier: Modifier = Modifier
) {
    val entries = entryProvider<NavKey> {
        entry<Route.Reader> {
            LibraryScreenRoute(
                modifier = modifier,
                onOpenSettings = { navigator.navigate(Route.Settings) }
            )
        }
        
        entry<Route.Settings> {
            com.genesys.feature.library.settings.SettingsScreenRoute(
                modifier = modifier,
                onBackClick = { navigator.popIfPossible() }
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
