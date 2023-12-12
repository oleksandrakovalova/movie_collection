package com.okproject.moviecollection.data.movie

import com.okproject.moviecollection.data.networking.MovieApi
import com.okproject.moviecollection.domain.movie.model.MovieDetailed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailsDataSource(
    private val api: MovieApi
) {
    suspend fun getMovie(id: Long): Result<MovieDetailed> = withContext(Dispatchers.IO) {
        try {
            val movie = api.getMovieDetails(movieId = id)
            Result.success(movie)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}