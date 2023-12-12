package com.okproject.moviecollection.ui.screens.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okproject.moviecollection.domain.movie.usecase.GetMovieDetailsUseCase
import com.okproject.moviecollection.ui.screens.util.toDetailsPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    movieId: Long,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
): ViewModel() {
    private val _movieDetailsState: MutableStateFlow<MovieState> = MutableStateFlow(MovieState.Loading)
    val movieDetailsState: StateFlow<MovieState> = _movieDetailsState.asStateFlow()

    init {
        loadMovieDetails(movieId)
    }

    private fun loadMovieDetails(movieId: Long) {
        viewModelScope.launch {
            _movieDetailsState.emit(MovieState.Loading)
            val movie = getMovieDetailsUseCase.invoke(movieId)
                .getOrNull()?.toDetailsPresentation()
            if (movie == null) {
                _movieDetailsState.emit(MovieState.Error)
            } else {
                _movieDetailsState.emit(MovieState.Loaded(data = movie))
            }
        }
    }
}