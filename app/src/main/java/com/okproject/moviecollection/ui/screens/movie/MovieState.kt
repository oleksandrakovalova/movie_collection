package com.okproject.moviecollection.ui.screens.movie

sealed interface MovieState {
    data class Loaded(val data: MovieDetailData): MovieState
    data object Loading: MovieState
    data object Error: MovieState
}

