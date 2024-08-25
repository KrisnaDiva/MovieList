package com.krisna.diva.movielist.di

import com.krisna.diva.movielist.core.domain.usecase.MovieInteractor
import com.krisna.diva.movielist.core.domain.usecase.MovieUseCase
import com.krisna.diva.movielist.ui.screen.detail.DetailViewModel
import com.krisna.diva.movielist.ui.screen.favorite.FavoriteViewModel
import com.krisna.diva.movielist.ui.screen.home.HomeViewModel
import com.krisna.diva.movielist.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { SplashViewModel() }
}