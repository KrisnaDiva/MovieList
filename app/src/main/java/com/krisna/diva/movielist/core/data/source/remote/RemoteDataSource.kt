package com.krisna.diva.movielist.core.data.source.remote

import com.krisna.diva.movielist.core.data.source.remote.network.ApiResponse
import com.krisna.diva.movielist.core.data.source.remote.network.ApiService
import com.krisna.diva.movielist.core.data.source.remote.response.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService) {
    suspend fun getPopularMovies(): Flow<ApiResponse<List<MovieItem>>> {
        return flow {
            try {
                val response = apiService.getPopularMovies()
                val dataArray = response.results?.filterNotNull()
                if (dataArray != null) {
                    if (dataArray.isNotEmpty()) {
                        emit(ApiResponse.Success(dataArray))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTopRatedMovies(): Flow<ApiResponse<List<MovieItem>>> {
        return flow {
            try {
                val response = apiService.getTopRatedMovies()
                val dataArray = response.results?.filterNotNull()
                if (dataArray != null) {
                    if (dataArray.isNotEmpty()) {
                        emit(ApiResponse.Success(dataArray))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}
