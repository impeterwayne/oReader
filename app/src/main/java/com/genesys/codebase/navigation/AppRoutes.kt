package com.genesys.codebase.navigation

import com.genesys.core.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data object ReaderRoute : Route

@Serializable
data object NotebookRoute : Route

enum class TopLevelDestination(
    val route: Route,
    val label: String,
    val badge: String
) {
    Reader(route = ReaderRoute, label = "Reader", badge = "RD"),
    Notebook(route = NotebookRoute, label = "Notebook", badge = "NB")
}
