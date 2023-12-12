package com.okproject.moviecollection.data.movie.usecase

import com.okproject.moviecollection.domain.movie.usecase.GetMovieDetailsUseCase
import com.okproject.moviecollection.domain.movie.MovieRepository
import com.okproject.moviecollection.domain.movie.model.MovieDetailed

class GetMovieDetailsUseCaseImpl(
    private val repository: MovieRepository,
): GetMovieDetailsUseCase {
    override suspend fun invoke(movieId: Long): Result<MovieDetailed> =
        repository.getMovieDetails(id = movieId)
}