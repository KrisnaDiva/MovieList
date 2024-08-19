package com.krisna.diva.movielist.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.krisna.diva.movielist.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val popularMovies = movieUseCase.getPopularMovies().asLiveData()
    val topRatedMovies = movieUseCase.getTopRatedMovies().asLiveData()
}