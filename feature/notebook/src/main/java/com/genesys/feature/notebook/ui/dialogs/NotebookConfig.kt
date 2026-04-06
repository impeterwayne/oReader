package com.genesys.feature.notebook.ui.dialogs

import android.text.format.DateFormat
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.core.model.notebook.Notebook
import com.genesys.feature.notebook.R
import com.genesys.feature.notebook.data.model.BackgroundType
import com.genesys.feature.notebook.ui.SnackConf
import com.genesys.feature.notebook.ui.SnackState
import kotlinx.coroutines.launch

/**
 * Notebook configuration dialog.
 * Ported from Notable's ui/dialogs/NotebookConfig.kt.
 * Allows editing the notebook title, default background, and viewing metadata.
 *
 * Note: Export and Move are stubbed until those features are ported.
 */
@Composable
fun NotebookConfigDialog(
    notebook: Notebook,
    notebookRepository: NotebookRepository,
    onClose: () -> Unit
) {
    val scope = rememberCoroutineScope()

    var bookTitle by remember { mutableStateOf(notebook.title) }
    val formattedCreatedAt = remember {
        DateFormat.format("dd MMM yyyy HH:mm", notebook.createdAt).toString()
    }
    val formattedUpdatedAt = remember {
        DateFormat.format("dd MMM yyyy HH:mm", notebook.updatedAt).toString()
    }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showBackgroundSelector by remember { mutableStateOf(false) }

    if (showBackgroundSelector) {
        BackgroundSelector(
            initialPageBackgroundType = notebook.defaultBackgroundType,
            initialPageBackground = notebook.defaultBackground,
            isNotebookBgSelector = true,
            notebookId = notebook.id,
            onChange = { backgroundType, background ->
                scope.launch {
                    val updated = if (background == null) {
                        notebook.copy(defaultBackgroundType = backgroundType)
                    } else {
                        notebook.copy(
                            defaultBackgroundType = backgroundType,
                            defaultBackground = background
                        )
                    }
                    notebookRepository.update(updated)
                }
            },
            onClose = { showBackgroundSelector = false }
        )
    }

    if (showDeleteDialog) {
        ShowSimpleConfirmationDialog(
            title = stringResource(R.string.notebook_config_delete_confirm_title),
            message = stringResource(R.string.notebook_config_delete_confirm_message, notebook.title),
            onConfirm = {
                scope.launch {
                    notebookRepository.delete(notebook.id)
                }
                showDeleteDialog = false
                onClose()
            },
            onCancel = { showDeleteDialog = false }
        )
        return
    }

    Dialog(
        onDismissRequest = { onClose() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        val focusManager = LocalFocusManager.current
        Surface(
            shape = RectangleShape,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(2.dp, Color.Black, RectangleShape)
        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(top = 24.dp, bottom = 16.dp)
        ) {
            // Header section
            Row(Modifier.padding(bottom = 16.dp)) {
                // Placeholder for page preview
                Box(
                    modifier = Modifier
                        .size(200.dp, 250.dp)
                        .background(Color.LightGray)
                        .border(1.dp, Color.Black, RectangleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = notebook.title.take(1).uppercase(),
                        fontSize = 48.sp,
                        color = Color.Gray
                    )
                }
                Spacer(Modifier.width(16.dp))
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    // Title field
                    Row {
                        Text(
                            text = stringResource(R.string.notebook_config_title),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        Spacer(Modifier.width(20.dp))
                        BasicTextField(
                            value = bookTitle,
                            textStyle = TextStyle(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Light,
                                fontSize = 24.sp
                            ),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            onValueChange = { bookTitle = it },
                            keyboardActions = KeyboardActions(onDone = {
                                focusManager.clearFocus()
                            }),
                            modifier = Modifier
                                .background(Color(230, 230, 230, 255))
                                .padding(10.dp, 0.dp)
                                .onFocusChanged { focusState ->
                                    if (!focusState.isFocused && notebook.title != bookTitle) {
                                        scope.launch {
                                            notebookRepository.update(notebook.copy(title = bookTitle))
                                        }
                                    }
                                }
                        )
                    }

                    // Default background selector
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.notebook_config_default_background))
                        Spacer(modifier = Modifier.width(40.dp))
                        Button(
                            onClick = { showBackgroundSelector = !showBackgroundSelector },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, Color.Black)
                        ) {
                            val typeName = when (BackgroundType.fromKey(notebook.defaultBackgroundType)) {
                                BackgroundType.AutoPdf -> stringResource(R.string.notebook_config_background_type_observe_pdf)
                                BackgroundType.CoverImage -> stringResource(R.string.notebook_config_background_type_cover_image)
                                BackgroundType.Image -> stringResource(R.string.notebook_config_background_type_image)
                                BackgroundType.ImageRepeating -> stringResource(R.string.notebook_config_background_type_repeating_image)
                                BackgroundType.Native -> stringResource(R.string.notebook_config_background_type_native)
                                is BackgroundType.Pdf -> stringResource(R.string.notebook_config_background_type_static_pdf)
                            }
                            Text(text = typeName, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.ArrowCircleRight,
                                contentDescription = "Expand selector",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Metadata
                    Text(stringResource(R.string.notebook_config_pages, notebook.pageIds.size))
                    Text(stringResource(R.string.notebook_config_created, formattedCreatedAt))
                    Text(stringResource(R.string.notebook_config_last_updated, formattedUpdatedAt))
                }
            }

            Spacer(Modifier.height(16.dp))

            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                NotebookActionButton(stringResource(R.string.notebook_config_btn_delete)) {
                    showDeleteDialog = true
                }
                NotebookActionButton(stringResource(R.string.notebook_config_btn_move)) {
                    SnackState.globalSnackFlow.tryEmit(SnackConf(text = "Move not yet implemented", duration = 2000))
                }
                NotebookActionButton(stringResource(R.string.notebook_config_btn_export)) {
                    SnackState.globalSnackFlow.tryEmit(SnackConf(text = "Export not yet implemented", duration = 2000))
                }
                NotebookActionButton(stringResource(R.string.notebook_config_btn_copy)) {
                    SnackState.globalSnackFlow.tryEmit(SnackConf(text = "Copy not yet implemented", duration = 2000))
                }
            }
        }
        }
    }
}

@Composable
fun NotebookActionButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp, 40.dp)
            .background(Color.LightGray, RectangleShape)
            .border(1.dp, Color.Black, RectangleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ShowSimpleConfirmationDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Dialog(onDismissRequest = onCancel) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .border(2.dp, Color.Black, RectangleShape)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(message)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .border(1.dp, Color.Black)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable { onCancel() }
                ) {
                    Text(stringResource(R.string.notebook_cancel))
                }
                Spacer(Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable { onConfirm() }
                ) {
                    Text(stringResource(R.string.notebook_config_btn_delete), color = Color.White)
                }
            }
        }
    }
}
