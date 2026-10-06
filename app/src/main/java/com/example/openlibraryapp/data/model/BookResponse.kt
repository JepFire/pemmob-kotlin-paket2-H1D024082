package com.example.openlibraryapp.data.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.net.URLDecoder
import java.net.URLEncoder

data class BookResponse(
    val docs: List<BookItem>?
)

data class BookItem(
    val key: String?,
    val title: String?,
    @SerializedName("author_name") val authorName: List<String>?,
    @SerializedName("first_publish_year") val firstPublishYear: Int?,
    @SerializedName("edition_count") val editionCount: Int?,
    val language: List<String>?
)

// Extension functions pada BookItem
fun BookItem.toEncodedJson(): String =
    URLEncoder.encode(Gson().toJson(this), "UTF-8")

fun String.toBookItem(): BookItem =
    Gson().fromJson(URLDecoder.decode(this, "UTF-8"), BookItem::class.java)
