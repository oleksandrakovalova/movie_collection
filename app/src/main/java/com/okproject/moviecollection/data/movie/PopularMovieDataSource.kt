package com.okproject.moviecollection.data.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.okproject.moviecollection.data.networking.MovieApi
import com.okproject.moviecollection.domain.movie.model.MovieSimplified

class PopularMovieDataSource(
    private val movieApi: MovieApi
): PagingSource<Int, MovieSimplified>() {
    override fun getRefreshKey(state: PagingState<Int, MovieSimplified>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSimplified> {
        val currentPage = params.key ?: 1
        return try {
            val moviesPage = movieApi.getPopularMovies(page = currentPage)
            LoadResult.Page(
                data = moviesPage.results,
                prevKey = null,
                nextKey = if (currentPage < moviesPage.totalPages) currentPage + 1 else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}