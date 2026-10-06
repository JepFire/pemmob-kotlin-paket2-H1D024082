package com.example.openlibraryapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.openlibraryapp.data.model.toBookItem
import com.example.openlibraryapp.data.model.toEncodedJson
import com.example.openlibraryapp.ui.screens.DetailScreen
import com.example.openlibraryapp.ui.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onBookClick = { book ->
                    navController.navigate("detail/${book.toEncodedJson()}")
                }
            )
        }
        composable(
            route = "detail/{bookJson}",
            arguments = listOf(navArgument("bookJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookJson = backStackEntry.arguments?.getString("bookJson") ?: ""
            val book = try {
                bookJson.toBookItem()
            } catch (e: Exception) {
                null
            }
            if (book != null) {
                DetailScreen(
                    book = book,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
