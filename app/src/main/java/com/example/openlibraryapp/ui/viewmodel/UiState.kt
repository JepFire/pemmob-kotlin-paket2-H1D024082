package com.example.openlibraryapp.ui.viewmodel

import com.example.openlibraryapp.data.model.BookItem

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val books: List<BookItem>) : UiState()
    data class Error(val message: String) : UiState()
}
