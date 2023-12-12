package com.okproject.moviecollection.data.networking

import com.okproject.moviecollection.data.networking.model.PagedResponse
import com.okproject.moviecollection.domain.movie.model.MovieDetailed
import com.okproject.moviecollection.domain.movie.model.MovieSimplified
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): PagedResponse<MovieSimplified>

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: Long): MovieDetailed
}