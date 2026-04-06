package com.genesys.feature.notebook.editor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.feature.notebook.R
import com.genesys.feature.notebook.data.deletePage
import com.genesys.feature.notebook.editor.canvas.CanvasEventBus
import com.genesys.feature.notebook.ui.noRippleClickable
import kotlinx.coroutines.launch


@Composable
fun PageMenu(
    pageRepository: NotebookPageRepository,
    notebookRepository: NotebookRepository,
    notebookId: String? = null,
    pageId: String,
    index: Int? = null,
    canDelete: Boolean,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    Popup(
        alignment = Alignment.TopStart,
        onDismissRequest = { onClose() },
        properties = PopupProperties(focusable = true)
    ) {
        Column(
            Modifier
                .border(1.dp, Color.Black, RectangleShape)
                .background(Color.White)
                .width(IntrinsicSize.Max)
        ) {
            if (notebookId != null && index != null) {
                Box(
                    Modifier
                        .padding(10.dp)
                        .noRippleClickable {
                            scope.launch {
                                notebookRepository.changePageIndex(
                                    notebookId,
                                    pageId,
                                    index - 1
                                )
                            }
                        }
                ) {
                    Text(stringResource(R.string.notebook_move_left))
                }

                Box(
                    Modifier
                        .padding(10.dp)
                        .noRippleClickable {
                            scope.launch {
                                notebookRepository.changePageIndex(
                                    notebookId,
                                    pageId,
                                    index + 1
                                )
                            }
                        }) {
                    Text(stringResource(R.string.notebook_move_right))
                }
                Box(
                    Modifier
                        .padding(10.dp)
                        .noRippleClickable {
                            scope.launch {
                                // Insert a new blank page after the current index
                                val currentPage = pageRepository.getById(pageId)
                                val notebook = notebookRepository.getById(notebookId) ?: return@launch
                                val newPage = notebook.newPage().let { p ->
                                    if (currentPage != null) p.copy(
                                        backgroundType = currentPage.backgroundType,
                                        background = currentPage.background
                                    ) else p
                                }
                                pageRepository.create(newPage)
                                notebookRepository.addPage(notebookId, newPage.id, index + 1)
                                CanvasEventBus.changePage.tryEmit(newPage.id)
                                onClose()
                            }
                        }) {
                    Text(stringResource(R.string.notebook_insert_after))
                }
            }

            Box(
                Modifier
                    .padding(10.dp)
                    .noRippleClickable {
                        scope.launch {
                            if (notebookId == null) return@launch
                            val currentPage = pageRepository.getById(pageId) ?: return@launch
                            val notebook = notebookRepository.getById(notebookId) ?: return@launch
                            val newPage = notebook.newPage().copy(
                                backgroundType = currentPage.backgroundType,
                                background = currentPage.background
                            )
                            pageRepository.create(newPage)
                            val insertIndex = if (index != null) index + 1 else null
                            notebookRepository.addPage(notebookId, newPage.id, insertIndex)
                            CanvasEventBus.duplicatePage.tryEmit(pageId to newPage.id)
                            onClose()
                        }
                    }) {
                Text(stringResource(R.string.notebook_duplicate))
            }
            if (canDelete) {
                Box(
                    Modifier
                        .padding(10.dp)
                        .noRippleClickable {
                            scope.launch {
                                deletePage(pageRepository, notebookRepository, pageId, context.filesDir)
                                onClose()
                            }
                        }) {
                    Text(stringResource(R.string.notebook_delete))
                }
            }
        }
    }
}
