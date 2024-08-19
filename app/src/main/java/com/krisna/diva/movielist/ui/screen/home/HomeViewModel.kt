package com.krisna.diva.movielist.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krisna.diva.movielist.data.MovieRepository
import com.krisna.diva.movielist.model.Movie
import com.krisna.diva.movielist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _popularMoviesState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val popularMoviesState: StateFlow<UiState<List<Movie>>> = _popularMoviesState

    private val _topRatedMoviesState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val topRatedMoviesState: StateFlow<UiState<List<Movie>>> = _topRatedMoviesState

    fun getPopularMovies() {
        viewModelScope.launch {
            repository.getPopularMovies()
                .catch {
                    _popularMoviesState.value = UiState.Error(it.message.toString())
                }
                .collect { movie ->
                    _popularMoviesState.value = UiState.Success(movie)
                }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            repository.getTopRatedMovies()
                .catch {
                    _topRatedMoviesState.value = UiState.Error(it.message.toString())
                }
                .collect { movie ->
                    _topRatedMoviesState.value = UiState.Success(movie)
                }
        }
    }
}