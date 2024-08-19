package com.krisna.diva.movielist.core.domain.usecase

import com.krisna.diva.movielist.core.data.source.remote.Resource
import com.krisna.diva.movielist.core.domain.model.Movie
import com.krisna.diva.movielist.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getPopularMovies()
    }

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getTopRatedMovies()
    }
}