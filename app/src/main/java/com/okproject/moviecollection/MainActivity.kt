package com.okproject.moviecollection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.okproject.moviecollection.ui.screens.popular.PopularMoviesScreen
import com.okproject.moviecollection.ui.screens.NavigationRoute
import com.okproject.moviecollection.ui.screens.movie.MovieScreen
import com.okproject.moviecollection.ui.screens.movie.MovieViewModel
import com.okproject.moviecollection.ui.theme.MovieCollectionTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.scope.KoinActivityScope
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(KoinExperimentalAPI::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieCollectionTheme {
                KoinAndroidContext {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MainNavGraph()
                    }
                }
            }
        }
    }
}

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Popular.route
    ) {
        composable(route = NavigationRoute.Popular.route) {
            PopularMoviesScreen(navController = navController)
        }
        composable(
            route = NavigationRoute.Movie.route,
            arguments = NavigationRoute.Movie.arguments
        ) {backStackEntry ->
            val movieId = backStackEntry.arguments?.getLong(NavigationRoute.Movie.MOVIE_ID_KEY) ?: 0L
            MovieScreen(
                navController = navController,
                viewModel = getViewModel<MovieViewModel>(
                    parameters = { parametersOf(movieId) }
                )
            )
        }
    }
}