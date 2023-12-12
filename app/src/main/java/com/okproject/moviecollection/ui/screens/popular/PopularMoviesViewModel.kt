package com.okproject.moviecollection.ui.screens.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.okproject.moviecollection.domain.movie.usecase.GetPopularMoviesUseCase
import com.okproject.moviecollection.ui.screens.util.toListItemPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PopularMoviesViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
): ViewModel() {
    private val _moviesState: MutableStateFlow<PagingData<MovieData>> = MutableStateFlow(PagingData.empty())
    val movieState: StateFlow<PagingData<MovieData>> = _moviesState.asStateFlow()

    init {
        showPopularMovies()
    }

    private fun showPopularMovies() {
        viewModelScope.launch {
            getMovies()
        }
    }

    private suspend fun getMovies() {
        getPopularMoviesUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .map { page ->
                page.map { movie ->
                    movie.toListItemPresentation()
                }
            }.collect {
                _moviesState.emit(it)
            }
    }
}