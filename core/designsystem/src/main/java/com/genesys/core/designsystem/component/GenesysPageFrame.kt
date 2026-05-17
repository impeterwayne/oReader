package com.genesys.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.genesys.core.designsystem.theme.GenesysTheme

@Composable
fun GenesysPageFrame(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues? = null,
    framed: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    val colors = GenesysTheme.colors
    val spacing = GenesysTheme.spacing
    val resolvedContentPadding = contentPadding ?: PaddingValues(spacing.md)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(spacing.sm)
    ) {
        val frameModifier = if (framed) {
            Modifier
                .background(colors.surface)
                .border(
                    width = GenesysTheme.strokes.thin,
                    color = colors.outlineVariant,
                    shape = GenesysTheme.shapes.large
                )
        } else {
            Modifier.background(colors.surface)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(frameModifier)
                .padding(resolvedContentPadding),
            content = content
        )
    }
}
