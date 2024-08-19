package com.krisna.diva.movielist.di

import com.krisna.diva.movielist.data.MovieRepository
import com.krisna.diva.movielist.data.remote.network.ApiConfig

object Injection {
    fun provideRepository(): MovieRepository {
        val apiService = ApiConfig.getApiService()
        return MovieRepository.getInstance(apiService)
    }
}