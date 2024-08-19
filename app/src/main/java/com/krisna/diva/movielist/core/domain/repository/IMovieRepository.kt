package com.krisna.diva.movielist.core.domain.repository

import com.krisna.diva.movielist.core.data.source.remote.Resource
import com.krisna.diva.movielist.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>
}