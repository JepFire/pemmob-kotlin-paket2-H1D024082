package com.example.openlibraryapp.data.repository

import com.example.openlibraryapp.data.model.BookResponse
import com.example.openlibraryapp.data.network.RetrofitClient

class BookRepository {
    private val api = RetrofitClient.api

    suspend fun searchBooks(query: String): BookResponse {
        return api.searchBooks(query)
    }
}
