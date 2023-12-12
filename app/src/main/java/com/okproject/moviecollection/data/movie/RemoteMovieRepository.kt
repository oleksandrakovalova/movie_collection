package com.okproject.moviecollection.data.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.okproject.moviecollection.domain.movie.MovieRepository
import com.okproject.moviecollection.domain.movie.model.MovieDetailed
import com.okproject.moviecollection.domain.movie.model.MovieSimplified
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RemoteMovieRepository(
    private val popularMovieDataSource: PopularMovieDataSource,
    private val movieDetailsDataSource: MovieDetailsDataSource
): MovieRepository {
    override suspend fun getPopularMovies(): Flow<PagingData<MovieSimplified>> =
        withContext(Dispatchers.IO) {
            Pager(
                config = PagingConfig(pageSize = MAX_PAGE_SIZE, prefetchDistance = 2),
                pagingSourceFactory = { popularMovieDataSource }
            ).flow
    }

    override suspend fun getMovieDetails(id: Long): Result<MovieDetailed> =
        movieDetailsDataSource.getMovie(id)
}

private const val MAX_PAGE_SIZE = 20