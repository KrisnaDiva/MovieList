package com.krisna.diva.movielist.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krisna.diva.movielist.core.domain.model.Movie
import com.krisna.diva.movielist.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _favoriteMoviesState = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMoviesState: StateFlow<List<Movie>> = _favoriteMoviesState

    init {
        fetchFavoriteMovies()
    }

    private fun fetchFavoriteMovies() {
        viewModelScope.launch {
            movieUseCase.getFavoriteMovies().collect { movies ->
                _favoriteMoviesState.value = movies
            }
        }
    }
}