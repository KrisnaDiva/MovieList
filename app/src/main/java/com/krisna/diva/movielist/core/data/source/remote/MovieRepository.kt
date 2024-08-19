package com.krisna.diva.movielist.core.data.source.remote

import com.krisna.diva.movielist.core.data.source.remote.network.ApiResponse
import com.krisna.diva.movielist.core.domain.model.Movie
import com.krisna.diva.movielist.core.domain.repository.IMovieRepository
import com.krisna.diva.movielist.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val remoteSource: RemoteDataSource
) : IMovieRepository {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        remoteSource.getPopularMovies().collect { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Success -> {
                    val domainMovies = DataMapper.mapResponsesToDomain(apiResponse.data)
                    emit(Resource.Success(domainMovies))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(emptyList()))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }
    }

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        remoteSource.getTopRatedMovies().collect { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Success -> {
                    val domainMovies = DataMapper.mapResponsesToDomain(apiResponse.data)
                    emit(Resource.Success(domainMovies))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(emptyList()))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }
    }
}
