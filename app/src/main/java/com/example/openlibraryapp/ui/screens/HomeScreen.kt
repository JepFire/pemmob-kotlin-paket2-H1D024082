package com.example.openlibraryapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.openlibraryapp.data.model.BookItem
import com.example.openlibraryapp.ui.viewmodel.BookViewModel
import com.example.openlibraryapp.ui.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: BookViewModel = viewModel(),
    onBookClick: (BookItem) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("OpenLibrary App") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Books") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { viewModel.searchBooks(searchQuery) }
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (uiState) {
                is UiState.Idle -> {
                    Text("Enter a keyword to search for books.")
                }
                is UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Error -> {
                    Text("Error: ${(uiState as UiState.Error).message}", color = MaterialTheme.colorScheme.error)
                }
                is UiState.Success -> {
                    val books = (uiState as UiState.Success).books
                    if (books.isEmpty()) {
                        Text("No books found.")
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(books) { book ->
                                BookListItem(book = book, onClick = { onBookClick(book) })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookListItem(book: BookItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = book.title ?: "Unknown Title", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Author: ${book.authorName?.joinToString(", ") ?: "Unknown"}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "First Publish Year: ${book.firstPublishYear ?: "Unknown"}", style = MaterialTheme.typography.labelSmall)
        }
    }
}
