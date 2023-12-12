package com.okproject.moviecollection.data.movie.usecase

import androidx.paging.PagingData
import com.okproject.moviecollection.domain.movie.usecase.GetPopularMoviesUseCase
import com.okproject.moviecollection.domain.movie.MovieRepository
import com.okproject.moviecollection.domain.movie.model.MovieSimplified
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCaseImpl(
    private val repository: MovieRepository
) : GetPopularMoviesUseCase {
    override suspend fun invoke(): Flow<PagingData<MovieSimplified>> =
        repository.getPopularMovies()
}