package com.krisna.diva.movielist.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krisna.diva.movielist.core.domain.model.Movie
import com.krisna.diva.movielist.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    fun checkFavoriteStatus(id: Int) {
        viewModelScope.launch {
            _isFavorite.value = movieUseCase.isMovieFavorite(id)
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            val newState = !_isFavorite.value
            if (newState) {
                movieUseCase.setFavoriteMovie(movie)
            } else {
                movieUseCase.removeFavoriteMovie(movie.id)
            }
            _isFavorite.value = newState
        }
    }
}