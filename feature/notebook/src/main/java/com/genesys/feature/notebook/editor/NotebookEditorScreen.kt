package com.genesys.feature.notebook.editor

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import com.genesys.core.designsystem.component.GenesysPrimaryButton
import com.genesys.core.designsystem.component.GenesysText
import com.genesys.core.designsystem.theme.GenesysTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NotebookEditorRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GenesysTheme.colors.surfaceDim)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.statusBarsPadding()
        ) {
            GenesysText(
                text = "Notebook Editor",
                style = GenesysTheme.typography.titleLarge
            )
            GenesysText(
                text = "This route is wired into the host app. Canvas persistence, page tools, and Onyx behavior follow in later tasks.",
                style = GenesysTheme.typography.bodyLarge,
                color = GenesysTheme.colors.outline
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .border(1.dp, Color(0xFF2D2A26))
                .background(Color(0xFFFFFBF5))
                .padding(12.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val step = 56.dp.toPx()
                val stroke = 1.dp.toPx()

                var x = 0f
                while (x < size.width) {
                    drawLine(
                        color = Color(0x1F000000),
                        start = Offset(x, 0f),
                        end = Offset(x, size.height),
                        strokeWidth = stroke
                    )
                    x += step
                }

                var y = 0f
                while (y < size.height) {
                    drawLine(
                        color = Color(0x12000000),
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = stroke
                    )
                    y += step
                }
            }
        }

        GenesysPrimaryButton(
            text = "Back to library",
            onClick = onBack,
            modifier = Modifier.height(48.dp)
        )
    }
}
