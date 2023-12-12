package com.okproject.moviecollection.domain.movie.usecase

import androidx.paging.PagingData
import com.okproject.moviecollection.domain.movie.model.MovieSimplified
import kotlinx.coroutines.flow.Flow

interface GetPopularMoviesUseCase {
    suspend fun invoke(): Flow<PagingData<MovieSimplified>>
}