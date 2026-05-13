package com.genesys.feature.library.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import com.genesys.feature.library.LibraryScreenRoute
import com.genesys.feature.library.LibrarySettingsRoute

@Composable
fun LibraryGraph(
    currentRoute: NavKey?,
    onOpenSettings: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (currentRoute) {
        is LibrarySettings -> {
            LibrarySettingsRoute(
                onBack = onBack,
                modifier = modifier
            )
        }

        else -> {
            LibraryScreenRoute(
                onOpenSettings = onOpenSettings,
                modifier = modifier
            )
        }
    }
}
