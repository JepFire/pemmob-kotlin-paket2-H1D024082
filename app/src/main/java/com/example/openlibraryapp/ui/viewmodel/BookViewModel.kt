package com.example.openlibraryapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openlibraryapp.data.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    private val repository = BookRepository()

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    fun searchBooks(query: String) {
        if (query.isBlank()) {
            _uiState.value = UiState.Idle
            return
        }

        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = repository.searchBooks(query)
                val books = response.docs ?: emptyList()
                _uiState.value = UiState.Success(books)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}
