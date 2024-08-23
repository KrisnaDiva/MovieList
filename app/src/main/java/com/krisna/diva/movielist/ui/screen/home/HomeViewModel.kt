package com.krisna.diva.movielist.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.krisna.diva.movielist.core.data.source.remote.Resource
import com.krisna.diva.movielist.core.domain.usecase.MovieUseCase
import com.krisna.diva.movielist.core.utils.DataMapper
import com.krisna.diva.movielist.ui.model.Movie
import kotlinx.coroutines.flow.map

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getPopularMovies(): LiveData<Resource<List<Movie>?>> {
        return movieUseCase.getPopularMovies()
            .map { resource ->
                when (resource) {
                    is Resource.Success -> Resource.Success(resource.data?.let { DataMapper.mapDomainToUi(it) })
                    is Resource.Error -> Resource.Error(resource.message ?: "Error")
                    is Resource.Loading -> Resource.Loading()
                }
            }
            .asLiveData()
    }

    fun getTopRatedMovies(): LiveData<Resource<List<Movie>?>> {
        return movieUseCase.getTopRatedMovies()
            .map { resource ->
                when (resource) {
                    is Resource.Success -> Resource.Success(resource.data?.let { DataMapper.mapDomainToUi(it) })
                    is Resource.Error -> Resource.Error(resource.message ?: "Error")
                    is Resource.Loading -> Resource.Loading()
                }
            }
            .asLiveData()
    }
}