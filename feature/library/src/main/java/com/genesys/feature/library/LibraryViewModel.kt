package com.genesys.feature.library

import com.genesys.core.common.base.BaseViewModel
import com.genesys.core.domain.usecase.library.GetLibrarySnapshotResult
import com.genesys.core.domain.usecase.library.LibraryUseCases
import com.genesys.core.domain.usecase.library.OpenLibraryBookResult
import com.genesys.core.domain.usecase.library.RemoveLibraryBookResult
import com.genesys.core.model.library.Book
import com.genesys.core.model.library.LibrarySnapshot
import com.genesys.core.model.library.OpenBookResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val libraryUseCases: LibraryUseCases,
    private val libraryEvents: LibraryEvents
) : BaseViewModel<LibraryUiState, LibrarySideEffect, LibraryAction>() {

    override val container = container<LibraryUiState, LibrarySideEffect>(LibraryUiState())

    val uiState: StateFlow<LibraryUiState> = container.stateFlow

    init {
        launchBlock {
            libraryEvents.updates.collect {
                onAction(LibraryAction.LibraryChangedExternally)
            }
        }
        onAction(LibraryAction.LoadLibrary)
    }

    override fun onAction(action: LibraryAction) {
        when (action) {
            LibraryAction.LoadLibrary -> loadLibrary()
            LibraryAction.RefreshLibrary -> refreshLibrary()
            LibraryAction.LibraryChangedExternally -> refreshLibrary()
            is LibraryAction.SelectPage -> selectPage(action.pageIndex)
            is LibraryAction.OpenBook -> openBook(action.book)
            is LibraryAction.RemoveBook -> removeBook(action.bookId)
        }
    }

    private fun loadLibrary() {
        refreshLibrary()
    }

    private fun refreshLibrary() = intent {
        reduce { state.copy(isLoading = true) }
        when (val result = libraryUseCases.getLibrarySnapshotUseCase()) {
            is GetLibrarySnapshotResult.Success -> {
                reduce { state.withSnapshot(result.snapshot, isLoading = false) }
            }
            is GetLibrarySnapshotResult.Failure -> {
                reduce {
                    state.copy(
                        isLoading = false,
                        books = emptyList(),
                        selectedFolders = emptyList(),
                        invalidFolders = emptyList(),
                        selectedPageIndex = 0
                    )
                }
                postSideEffect(LibrarySideEffect.ShowMessage("Could not load your library"))
            }
        }
    }


    private fun selectPage(pageIndex: Int) = intent {
        reduce {
            val maxPageIndex = if (state.totalPages == 0) 0 else state.totalPages - 1
            state.copy(selectedPageIndex = pageIndex.coerceIn(0, maxPageIndex))
        }
    }

    private fun openBook(book: Book) = intent {
        reduce { state.copy(isLoading = true) }
        when (val outcome = libraryUseCases.openLibraryBookUseCase(book)) {
            is OpenLibraryBookResult.Success -> {
                when (val result = outcome.result) {
                    is OpenBookResult.Available -> {
                        reduce { state.copy(isLoading = false) }
                        postSideEffect(LibrarySideEffect.OpenBook(result.filePath))
                    }
                    is OpenBookResult.Unavailable -> {
                        reduce { state.copy(isLoading = false) }
                        postSideEffect(LibrarySideEffect.ShowMessage(result.reason))
                    }
                }
            }
            is OpenLibraryBookResult.Failure -> {
                reduce { state.copy(isLoading = false) }
                postSideEffect(LibrarySideEffect.ShowMessage("Could not open that book"))
            }
        }
    }

    private fun removeBook(bookId: String) = intent {
        reduce { state.copy(isLoading = true) }
        when (val result = libraryUseCases.removeLibraryBookUseCase(bookId)) {
            is RemoveLibraryBookResult.Success -> {
                reduce { state.withSnapshot(result.snapshot, isLoading = false) }
                result.removedBook?.let { book ->
                    postSideEffect(LibrarySideEffect.ShowMessage("Removed ${book.title}"))
                }
            }
            is RemoveLibraryBookResult.Failure -> {
                reduce { state.copy(isLoading = false) }
                postSideEffect(LibrarySideEffect.ShowMessage("Could not remove that book"))
            }
        }
    }

    private fun LibraryUiState.withSnapshot(
        snapshot: LibrarySnapshot,
        isLoading: Boolean = this.isLoading
    ): LibraryUiState {
        val totalPages = if (snapshot.books.isEmpty()) 0 else ((snapshot.books.size - 1) / pageSize) + 1
        val resolvedPageIndex = if (totalPages == 0) 0 else selectedPageIndex.coerceIn(0, totalPages - 1)
        return copy(
            isLoading = isLoading,
            books = snapshot.books,
            selectedFolders = snapshot.selectedFolders,
            invalidFolders = snapshot.invalidFolders,
            selectedPageIndex = resolvedPageIndex
        )
    }
}


