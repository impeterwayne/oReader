package com.genesys.codebase.navigation

import androidx.annotation.DrawableRes
import com.genesys.core.navigation.Route
import kotlinx.serialization.Serializable
import com.genesys.core.designsystem.R as DesignSystemR

@Serializable
data object ReaderRoute : Route

@Serializable
data object NotebookRoute : Route

enum class TopLevelDestination(
    val route: Route,
    val label: String,
    @DrawableRes val iconResId: Int
) {
    Reader(route = ReaderRoute, label = "Reader", iconResId = DesignSystemR.drawable.ic_book_open),
    Notebook(route = NotebookRoute, label = "Notebook", iconResId = DesignSystemR.drawable.ic_notebook)
}
