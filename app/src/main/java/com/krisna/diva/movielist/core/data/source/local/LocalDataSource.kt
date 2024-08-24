package com.krisna.diva.movielist.core.data.source.local

import com.krisna.diva.movielist.core.data.source.local.entity.MovieEntity
import com.krisna.diva.movielist.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    suspend fun insertFavoriteMovie(movie: MovieEntity) = movieDao.insertFavoriteMovie(movie)

    suspend fun deleteFavoriteMovie(id: Int) = movieDao.deleteFavoriteMovie(id)

    suspend fun isMovieFavorite(id: Int): Boolean = movieDao.isMovieFavorite(id)

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getAllFavoriteMovie()
}