package com.genesys.codebase.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme

private val bottomDestinations = TopLevelDestination.entries

@Composable
fun AppBottomBar(
    currentDestination: TopLevelDestination,
    onDestinationSelected: (TopLevelDestination) -> Unit,
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
            BottomBarItem(
                destination = destination,
                selected = currentDestination == destination,
                onClick = { onDestinationSelected(destination) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    destination: TopLevelDestination,
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
        Image(
            painter = painterResource(id = destination.iconResId),
            contentDescription = destination.label,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(contentColor)
        )

        GenesysText(
            text = destination.label,
            style = GenesysTheme.typography.labelMedium.copy(textAlign = TextAlign.Center),
            color = contentColor
        )
    }
}
