package com.okproject.moviecollection.domain.movie.usecase

import com.okproject.moviecollection.domain.movie.model.MovieDetailed

interface GetMovieDetailsUseCase {
    suspend fun invoke(movieId: Long): Result<MovieDetailed>
}