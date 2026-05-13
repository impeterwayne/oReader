package com.genesys.codebase.reader

import android.net.Uri
import com.genesys.core.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class ReaderLibraryViewModel @Inject constructor(
    private val repository: ReaderLibraryRepository,
    private val libraryEvents: ReaderLibraryEvents
) : BaseViewModel<ReaderUiState, ReaderSideEffect, ReaderAction>() {

    override val container = container<ReaderUiState, ReaderSideEffect>(ReaderUiState())

    val uiState: StateFlow<ReaderUiState> = container.stateFlow
    val sideEffects: Flow<ReaderSideEffect> = container.sideEffectFlow

    init {
        launchBlock {
            libraryEvents.updates.collect {
                onAction(ReaderAction.LibraryChangedExternally)
            }
        }
        onAction(ReaderAction.LoadLibrary)
    }

    override fun onAction(action: ReaderAction) {
        when (action) {
            ReaderAction.LoadLibrary -> loadLibrary()
            ReaderAction.RefreshLibrary -> refreshLibrary()
            ReaderAction.LibraryChangedExternally -> refreshLibrary()
            is ReaderAction.AddLibraryFolder -> addLibraryFolder(action.folderUri)
            is ReaderAction.RemoveLibraryFolder -> removeLibraryFolder(action.folderId)
            ReaderAction.RemoveInvalidFolders -> removeInvalidFolders()
            is ReaderAction.OpenBook -> openBook(action.book)
            is ReaderAction.RemoveBook -> removeBook(action.bookId)
        }
    }

    private fun loadLibrary() {
        refreshLibrary()
    }

    private fun refreshLibrary() = intent {
        reduce { state.copy(isLoading = true) }

        runCatching { repository.getLibrarySnapshot() }
            .onSuccess { snapshot ->
                reduce {
                    state.copy(
                        isLoading = false,
                        books = snapshot.books,
                        selectedFolders = snapshot.selectedFolders,
                        invalidFolders = snapshot.invalidFolders
                    )
                }
            }
            .onFailure {
                reduce {
                    state.copy(
                        isLoading = false,
                        selectedFolders = emptyList(),
                        invalidFolders = emptyList()
                    )
                }
                postSideEffect(ReaderSideEffect.ShowMessage("Could not load your library"))
            }
    }

    private fun addLibraryFolder(folderUri: Uri) = intent {
        runCatching {
            val added = repository.addLibraryFolder(folderUri)
            val snapshot = repository.getLibrarySnapshot()
            added to snapshot
        }.onSuccess { (added, snapshot) ->
            reduce {
                state.copy(
                    isLoading = false,
                    books = snapshot.books,
                    selectedFolders = snapshot.selectedFolders,
                    invalidFolders = snapshot.invalidFolders
                )
            }
            postSideEffect(
                ReaderSideEffect.ShowMessage(
                    if (added) "Library folder added" else "Library folder already added"
                )
            )
        }.onFailure {
            reduce { state.copy(isLoading = false) }
            postSideEffect(ReaderSideEffect.ShowMessage("Could not add that folder"))
        }
    }

    private fun removeLibraryFolder(folderId: String) = intent {
        runCatching {
            val removedFolder = repository.removeLibraryFolder(folderId)
            val snapshot = repository.getLibrarySnapshot()
            removedFolder to snapshot
        }.onSuccess { (removedFolder, snapshot) ->
            reduce {
                state.copy(
                    isLoading = false,
                    books = snapshot.books,
                    selectedFolders = snapshot.selectedFolders,
                    invalidFolders = snapshot.invalidFolders
                )
            }
            postSideEffect(
                ReaderSideEffect.ShowMessage(
                    removedFolder?.let { folder -> "Removed ${folder.displayName}" }
                        ?: "Folder already removed"
                )
            )
        }.onFailure {
            reduce { state.copy(isLoading = false) }
            postSideEffect(ReaderSideEffect.ShowMessage("Could not remove that folder"))
        }
    }

    private fun removeInvalidFolders() = intent {
        runCatching {
            val removed = repository.removeInvalidFolders()
            val snapshot = repository.getLibrarySnapshot()
            removed to snapshot
        }.onSuccess { (removed, snapshot) ->
            reduce {
                state.copy(
                    isLoading = false,
                    books = snapshot.books,
                    selectedFolders = snapshot.selectedFolders,
                    invalidFolders = snapshot.invalidFolders
                )
            }
            postSideEffect(
                ReaderSideEffect.ShowMessage(
                    if (removed) "Removed invalid folders" else "No invalid folders to remove"
                )
            )
        }.onFailure {
            reduce { state.copy(isLoading = false) }
            postSideEffect(ReaderSideEffect.ShowMessage("Could not clean unavailable folders"))
        }
    }

    private fun openBook(book: ReaderBook) = intent {
        runCatching { repository.openBook(book) }
            .onSuccess { result ->
                when (result) {
                    is ReaderLibraryRepository.OpenBookResult.Available -> {
                        postSideEffect(ReaderSideEffect.OpenBook(result.filePath))
                    }
                    is ReaderLibraryRepository.OpenBookResult.Unavailable -> {
                        postSideEffect(ReaderSideEffect.ShowMessage(result.reason))
                    }
                }
            }
            .onFailure {
                postSideEffect(ReaderSideEffect.ShowMessage("Could not open that book"))
            }
    }

    private fun removeBook(bookId: String) = intent {
        runCatching {
            val removedBook = repository.removeBook(bookId)
            val snapshot = repository.getLibrarySnapshot()
            removedBook to snapshot
        }.onSuccess { (removedBook, snapshot) ->
            reduce {
                state.copy(
                    isLoading = false,
                    books = snapshot.books,
                    selectedFolders = snapshot.selectedFolders,
                    invalidFolders = snapshot.invalidFolders
                )
            }
            removedBook?.let { book ->
                postSideEffect(ReaderSideEffect.ShowMessage("Removed ${book.title}"))
            }
        }.onFailure {
            postSideEffect(ReaderSideEffect.ShowMessage("Could not remove that book"))
        }
    }
}
