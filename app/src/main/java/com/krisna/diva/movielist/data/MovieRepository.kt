package com.krisna.diva.movielist.data

import com.krisna.diva.movielist.data.remote.network.ApiService
import com.krisna.diva.movielist.model.Movie
import com.krisna.diva.movielist.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository private constructor(private val apiService: ApiService) {
    fun getPopularMovies(): Flow<List<Movie>> = flow {
        try {
            val response = apiService.getPopularMovies()
            if (response.isSuccessful) {
                val resultsItems = response.body()?.results ?: emptyList()
                val movies = DataMapper.mapResultsItemToMovie(resultsItems)
                emit(movies)
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun getTopRatedMovies(): Flow<List<Movie>> = flow {
        try {
            val response = apiService.getTopRatedMovies()
            if (response.isSuccessful) {
                val resultsItems = response.body()?.results ?: emptyList()
                val movies = DataMapper.mapResultsItemToMovie(resultsItems)
                emit(movies)
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(apiService: ApiService): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(apiService).also { instance = it }
            }
    }
}