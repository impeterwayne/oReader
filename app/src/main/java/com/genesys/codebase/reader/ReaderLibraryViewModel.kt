package com.genesys.codebase.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ReaderLibraryUiState(
    val isLoading: Boolean = true,
    val books: List<ReaderBook> = emptyList(),
    val storageAccessRequirement: ReaderStorageAccessRequirement = ReaderStorageAccessRequirement.None,
    val message: String? = null
)

@HiltViewModel
class ReaderLibraryViewModel @Inject constructor(
    private val repository: ReaderLibraryRepository,
    private val libraryEvents: ReaderLibraryEvents
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReaderLibraryUiState())
    val uiState: StateFlow<ReaderLibraryUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            libraryEvents.updates.collect {
                refreshBooks()
            }
        }
        refreshBooks()
    }

    fun refreshBooks() {
        viewModelScope.launch {
            _uiState.update { current ->
                current.copy(isLoading = true, message = null)
            }

            runCatching {
                repository.getLibrarySnapshot()
            }.onSuccess { snapshot ->
                _uiState.update { current ->
                    current.copy(
                        isLoading = false,
                        books = snapshot.books,
                        storageAccessRequirement = snapshot.storageAccessRequirement
                    )
                }
            }.onFailure {
                _uiState.update { current ->
                    current.copy(
                        isLoading = false,
                        storageAccessRequirement = ReaderStorageAccessRequirement.None,
                        message = "Could not load your library"
                    )
                }
            }
        }
    }

    fun removeBook(bookId: String) {
        viewModelScope.launch {
            runCatching {
                val removedBook = repository.removeBook(bookId)
                val snapshot = repository.getLibrarySnapshot()
                snapshot to removedBook
            }.onSuccess { (snapshot, removedBook) ->
                _uiState.update { current ->
                    current.copy(
                        isLoading = false,
                        books = snapshot.books,
                        storageAccessRequirement = snapshot.storageAccessRequirement,
                        message = removedBook?.let { "Removed ${it.title}" }
                    )
                }
            }.onFailure {
                _uiState.update { current ->
                    current.copy(message = "Could not remove that book")
                }
            }
        }
    }

    fun consumeMessage() {
        _uiState.update { current ->
            current.copy(message = null)
        }
    }
}
