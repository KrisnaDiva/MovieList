package com.krisna.diva.movielist.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krisna.diva.movielist.core.data.source.remote.Resource
import com.krisna.diva.movielist.core.domain.model.Movie
import com.krisna.diva.movielist.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _popularMoviesState = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val popularMoviesState: StateFlow<Resource<List<Movie>>> = _popularMoviesState

    private val _topRatedMoviesState = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val topRatedMoviesState: StateFlow<Resource<List<Movie>>> = _topRatedMoviesState

    init {
        fetchPopularMovies()
        fetchTopRatedMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            movieUseCase.getPopularMovies().collect { resource ->
                _popularMoviesState.value = resource
            }
        }
    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            movieUseCase.getTopRatedMovies().collect { resource ->
                _topRatedMoviesState.value = resource
            }
        }
    }
}
