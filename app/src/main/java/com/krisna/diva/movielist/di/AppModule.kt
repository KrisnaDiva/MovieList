package com.krisna.diva.movielist.di

import com.krisna.diva.movielist.core.domain.usecase.MovieInteractor
import com.krisna.diva.movielist.core.domain.usecase.MovieUseCase
import com.krisna.diva.movielist.ui.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}