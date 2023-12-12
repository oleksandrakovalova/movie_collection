package com.okproject.moviecollection.ui.screens

import com.okproject.moviecollection.ui.screens.movie.MovieViewModel
import com.okproject.moviecollection.ui.screens.popular.PopularMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiScreens = module {
    viewModelOf(::PopularMoviesViewModel)
    viewModel { MovieViewModel(get(), get()) }
}