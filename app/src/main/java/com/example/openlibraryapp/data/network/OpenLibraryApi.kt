package com.example.openlibraryapp.data.network

import com.example.openlibraryapp.data.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApi {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("limit") limit: Int = 20
    ): BookResponse
}
