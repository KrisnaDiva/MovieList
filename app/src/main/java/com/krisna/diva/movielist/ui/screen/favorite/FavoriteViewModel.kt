package com.krisna.diva.movielist.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krisna.diva.movielist.core.data.source.remote.Resource
import com.krisna.diva.movielist.core.domain.model.Movie
import com.krisna.diva.movielist.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _favoriteMoviesState = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val favoriteMoviesState: StateFlow<Resource<List<Movie>>> = _favoriteMoviesState

    init {
        fetchFavoriteMovies()
    }

    private fun fetchFavoriteMovies() {
        viewModelScope.launch {
            try {
                movieUseCase.getFavoriteMovies().collect { movies ->
                    _favoriteMoviesState.value = Resource.Success(movies)
                }
            } catch (exception: Exception) {
                _favoriteMoviesState.value = Resource.Error(exception.message ?: "An error occurred")
            }
        }
    }
}
