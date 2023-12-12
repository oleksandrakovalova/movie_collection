package com.okproject.moviecollection.ui.screens

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavigationRoute {
    data object Popular: NavigationRoute {
        val route: String = "popular"
    }

    data object Movie: NavigationRoute {
        const val MOVIE_ID_KEY = "movieId"
        const val route: String = "movie/{$MOVIE_ID_KEY}"
        val arguments = listOf(
            navArgument(MOVIE_ID_KEY) {
                type = NavType.LongType
            }
        )
        fun direction(movieId: Long) = "movie/$movieId"
    }
}
