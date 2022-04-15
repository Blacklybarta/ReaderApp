package com.example.readerapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.readerapp.screens.SplashScreen
import com.example.readerapp.screens.details.BookDetailsScreen
import com.example.readerapp.screens.home.HomeScreen
import com.example.readerapp.screens.login.LoginScreen
import com.example.readerapp.screens.search.BookSearchScreen
import com.example.readerapp.screens.search.BooksSearchViewModel
import com.example.readerapp.screens.stats.StatScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name) {

        composable(ReaderScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(ReaderScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(ReaderScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(ReaderScreens.StatsScreen.name) {
            StatScreen(navController = navController)
        }

        val detailName = ReaderScreens.DetailsScreen.name
        composable(
            "$detailName/{bookId}",
            arguments = listOf(navArgument(name = "bookId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("bookId").let {
                BookDetailsScreen(navController = navController, it.toString())
            }
        }

        composable(ReaderScreens.SearchScreen.name) {
            val searchViewModel = hiltViewModel<BooksSearchViewModel>()
            BookSearchScreen(navController = navController, viewModel = searchViewModel)
        }

    }
}