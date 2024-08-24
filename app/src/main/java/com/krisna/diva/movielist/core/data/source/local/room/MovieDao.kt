package com.krisna.diva.movielist.core.data.source.local.room

import androidx.room.*
import com.krisna.diva.movielist.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie: MovieEntity)

    @Query("DELETE FROM favorite_movie WHERE id = :id")
    suspend fun deleteFavoriteMovie(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_movie WHERE id = :id LIMIT 1)")
    suspend fun isMovieFavorite(id: Int): Boolean

    @Query("SELECT * FROM favorite_movie")
    fun getAllFavoriteMovie(): Flow<List<MovieEntity>>
}
