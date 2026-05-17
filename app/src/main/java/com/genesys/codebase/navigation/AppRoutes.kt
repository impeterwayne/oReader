package com.genesys.codebase.navigation

import androidx.annotation.DrawableRes
import com.genesys.core.navigation.Route
import com.genesys.core.designsystem.R as DesignSystemR

enum class TopLevelDestination(
    val route: Route,
    val label: String,
    @DrawableRes val iconResId: Int
) {
    Reader(route = Route.Reader, label = "Reader", iconResId = DesignSystemR.drawable.ic_book_open),
    Notebook(route = Route.Notebook, label = "Notebook", iconResId = DesignSystemR.drawable.ic_notebook)
}
