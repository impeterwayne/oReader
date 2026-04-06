package com.genesys.feature.notebook.editor

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.genesys.core.domain.repository.notebook.NotebookPageRepository
import com.genesys.core.domain.repository.notebook.NotebookRepository
import com.genesys.feature.notebook.editor.canvas.CanvasEventBus
import com.genesys.feature.notebook.editor.ui.toolbar.ToolbarButton
import com.genesys.feature.notebook.editor.utils.autoEInkAnimationOnScroll
import com.genesys.feature.notebook.ui.noRippleClickable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
import kotlin.math.roundToInt

data class NotebookQuickNavUiState(
    val isLoading: Boolean = true,
    val currentPageId: String? = null,
    val notebookTitle: String = "Notebook",
    val pageIds: List<String> = emptyList(),
    val currentIndex: Int = 0
)

@HiltViewModel
class NotebookQuickNavViewModel @Inject constructor(
    private val notebookRepository: NotebookRepository,
    private val pageRepository: NotebookPageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotebookQuickNavUiState())
    val uiState: StateFlow<NotebookQuickNavUiState> = _uiState.asStateFlow()

    fun load(currentPageId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val page = pageRepository.getById(currentPageId)
            val notebook = page?.notebookId?.let { notebookRepository.getById(it) }
            val pageIds = notebook?.pageIds?.ifEmpty { listOf(currentPageId) } ?: listOf(currentPageId)
            val currentIndex = pageIds.indexOf(currentPageId).takeIf { it >= 0 } ?: 0

            _uiState.value = NotebookQuickNavUiState(
                isLoading = false,
                currentPageId = currentPageId,
                notebookTitle = notebook?.title ?: "Notebook",
                pageIds = pageIds,
                currentIndex = currentIndex
            )
        }
    }

    fun beginPreview() {
        CanvasEventBus.saveCurrent.tryEmit(Unit)
    }

    fun previewIndex(index: Int) {
        uiState.value.pageIds.getOrNull(index)?.let(CanvasEventBus.previewPage::tryEmit)
    }

    fun commitIndex(index: Int) {
        CanvasEventBus.restoreCanvas.tryEmit(Unit)
        uiState.value.pageIds.getOrNull(index)?.let(CanvasEventBus.changePage::tryEmit)
    }

    fun openPage(pageId: String) {
        CanvasEventBus.restoreCanvas.tryEmit(Unit)
        CanvasEventBus.changePage.tryEmit(pageId)
    }

    fun restoreCanvas() {
        CanvasEventBus.restoreCanvas.tryEmit(Unit)
    }
}

@Composable
fun NotebookQuickNav(
    currentPageId: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NotebookQuickNavViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(currentPageId) {
        viewModel.load(currentPageId)
    }

    NotebookQuickNavContent(
        uiState = uiState,
        modifier = modifier,
        onClose = {
            viewModel.restoreCanvas()
            onClose()
        },
        onOpenPage = { pageId ->
            viewModel.openPage(pageId)
            onClose()
        },
        onPreviewStart = viewModel::beginPreview,
        onPreviewIndex = viewModel::previewIndex,
        onPreviewCommit = { index ->
            viewModel.commitIndex(index)
            onClose()
        }
    )
}

@Composable
private fun NotebookQuickNavContent(
    uiState: NotebookQuickNavUiState,
    onClose: () -> Unit,
    onOpenPage: (String) -> Unit,
    onPreviewStart: () -> Unit,
    onPreviewIndex: (Int) -> Unit,
    onPreviewCommit: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Spacer(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .noRippleClickable(onClose)
        )

        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.Black)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material.Text(
                    text = uiState.notebookTitle,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                ToolbarButton(
                    text = "x",
                    contentDescription = "close quick navigation",
                    onSelect = onClose
                )
            }

            if (uiState.pageIds.isNotEmpty()) {
                androidx.compose.material.Text(
                    text = "Pages",
                    color = Color.DarkGray
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .autoEInkAnimationOnScroll(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(uiState.pageIds, key = { _, pageId -> pageId }) { index, pageId ->
                        PagePreviewCard(
                            pageId = pageId,
                            pageNumber = index + 1,
                            isSelected = pageId == uiState.currentPageId,
                            onOpen = { onOpenPage(pageId) }
                        )
                    }
                }

                if (uiState.pageIds.size > 1 && !uiState.isLoading) {
                    NotebookPageSlider(
                        pageCount = uiState.pageIds.size,
                        currentIndex = uiState.currentIndex,
                        onPreviewStart = onPreviewStart,
                        onPreviewIndexChanged = onPreviewIndex,
                        onPreviewCommitted = onPreviewCommit
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PagePreviewCard(
    pageId: String,
    pageNumber: Int,
    isSelected: Boolean,
    onOpen: () -> Unit
) {
    val context = LocalContext.current
    val previewBitmap by produceState<android.graphics.Bitmap?>(initialValue = null, pageId) {
        value = kotlinx.coroutines.withContext(Dispatchers.IO) {
            val file = File(context.filesDir, "pages/previews/thumbs/$pageId")
            if (file.exists()) BitmapFactory.decodeFile(file.absolutePath) else null
        }
    }
    var showOutline by rememberSaveable(pageId) { mutableStateOf(false) }

    Box {
        Box(
            modifier = Modifier
                .width(96.dp)
                .aspectRatio(3f / 4f)
                .border(
                    width = if (isSelected || showOutline) 2.dp else 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(2.dp)
                )
                .background(Color(0xFFF1EEE7))
                .combinedClickable(
                    onClick = onOpen,
                    onLongClick = { showOutline = !showOutline }
                ),
            contentAlignment = Alignment.Center
        ) {
            if (previewBitmap != null) {
                Image(
                    bitmap = previewBitmap!!.asImageBitmap(),
                    contentDescription = "Page $pageNumber preview",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                androidx.compose.material.Text(
                    text = pageNumber.toString(),
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
private fun NotebookPageSlider(
    pageCount: Int,
    currentIndex: Int,
    onPreviewStart: () -> Unit,
    onPreviewIndexChanged: (Int) -> Unit,
    onPreviewCommitted: (Int) -> Unit,
    modifier: Modifier = Modifier,
    trackHeight: Dp = 26.dp,
    thumbWidth: Dp = 20.dp
) {
    var dragIndex by remember(currentIndex, pageCount) {
        mutableIntStateOf(currentIndex.coerceIn(0, (pageCount - 1).coerceAtLeast(0)))
    }
    var containerWidthPx by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val thumbOffsetPx = if (pageCount <= 1 || containerWidthPx <= 0) {
        0f
    } else {
        val fraction = dragIndex.toFloat() / (pageCount - 1)
        (containerWidthPx - with(density) { thumbWidth.toPx() })
            .coerceAtLeast(0f) * fraction
    }

    fun indexFromX(x: Float): Int {
        if (pageCount <= 1 || containerWidthPx <= 0) return 0
        val fraction = (x / containerWidthPx.toFloat()).coerceIn(0f, 1f)
        return (fraction * (pageCount - 1)).roundToInt()
    }

    Column(modifier = modifier.fillMaxWidth()) {
        androidx.compose.material.Text(
            text = "${dragIndex + 1} / $pageCount",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth()
                .height(trackHeight)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                .onGloballyPositioned { coordinates ->
                    containerWidthPx = coordinates.size.width
                }
                .pointerInput(pageCount, currentIndex) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            onPreviewStart()
                            val nextIndex = indexFromX(offset.x)
                            dragIndex = nextIndex
                            onPreviewIndexChanged(nextIndex)
                        },
                        onDragEnd = { onPreviewCommitted(dragIndex) },
                        onDragCancel = { onPreviewCommitted(dragIndex) }
                    ) { change, _ ->
                        change.consume()
                        val nextIndex = indexFromX(change.position.x)
                        if (nextIndex != dragIndex) {
                            dragIndex = nextIndex
                            onPreviewIndexChanged(nextIndex)
                        }
                    }
                }
                .pointerInput(pageCount, currentIndex) {
                    detectTapGestures { offset ->
                        val nextIndex = indexFromX(offset.x)
                        dragIndex = nextIndex
                        onPreviewCommitted(nextIndex)
                    }
                }
                .autoEInkAnimationOnScroll()
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 2.dp, vertical = 2.dp)
                    .offset { IntOffset(thumbOffsetPx.roundToInt(), 0) }
                    .height(trackHeight - 4.dp)
                    .width(thumbWidth)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            )
        }
    }
}
