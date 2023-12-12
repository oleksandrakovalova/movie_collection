package com.okproject.moviecollection.domain.movie

import androidx.paging.PagingData
import com.okproject.moviecollection.domain.movie.model.MovieDetailed
import com.okproject.moviecollection.domain.movie.model.MovieSimplified
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): Flow<PagingData<MovieSimplified>>
    suspend fun getMovieDetails(id: Long): Result<MovieDetailed>
}