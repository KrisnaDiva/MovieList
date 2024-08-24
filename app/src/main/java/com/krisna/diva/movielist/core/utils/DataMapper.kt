package com.krisna.diva.movielist.core.utils

import com.krisna.diva.movielist.core.data.source.local.entity.MovieEntity
import com.krisna.diva.movielist.core.data.source.remote.response.MovieItemDto
import com.krisna.diva.movielist.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToDomain(input: List<MovieItemDto>): List<Movie> {
        return input.map {
            Movie(
                id = it.id ?: 0,
                title = it.title ?: "",
                image = "https://image.tmdb.org/t/p/w500${it.image}",
                rating = it.rating ?: 0.0,
                genres = GenreMapper.mapGenreIdsToNames(it.genres),
                overview = it.overview ?: ""
            )
        }
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(
                id = it.id,
                title = it.title ?: "",
                image = "https://image.tmdb.org/t/p/w500${it.image}",
                rating = it.rating ?: 0.0,
                genres = it.genres ?: emptyList(),
                overview = it.overview ?: ""
            )
        }
    }

    fun mapDomainToEntity(input: Movie): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            image = input.image,
            rating = input.rating,
            genres = input.genres,
            overview = input.overview
        )
    }
}