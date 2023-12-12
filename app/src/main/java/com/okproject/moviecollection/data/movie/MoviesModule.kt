package com.okproject.moviecollection.data.movie

import com.okproject.moviecollection.data.movie.usecase.GetMovieDetailsUseCaseImpl
import com.okproject.moviecollection.data.movie.usecase.GetPopularMoviesUseCaseImpl
import com.okproject.moviecollection.domain.movie.usecase.GetPopularMoviesUseCase
import com.okproject.moviecollection.domain.movie.MovieRepository
import com.okproject.moviecollection.domain.movie.usecase.GetMovieDetailsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val movies = module {
    singleOf(::PopularMovieDataSource)
    singleOf(::MovieDetailsDataSource)
    singleOf(::RemoteMovieRepository) bind MovieRepository::class
    singleOf(::GetPopularMoviesUseCaseImpl) bind GetPopularMoviesUseCase::class
    singleOf(::GetMovieDetailsUseCaseImpl) bind GetMovieDetailsUseCase::class
}