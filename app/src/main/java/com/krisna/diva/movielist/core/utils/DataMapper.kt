package com.krisna.diva.movielist.core.utils

import com.krisna.diva.movielist.core.data.source.remote.response.MovieItemDto
import com.krisna.diva.movielist.core.domain.model.Movie
import com.krisna.diva.movielist.ui.model.Movie

object DataMapper {
    fun mapResponsesToDomain(input: List<MovieItemDto>): List<Movie> {
        return input.map {
            Movie(
                title = it.title ?: "",
                image = "https://image.tmdb.org/t/p/w500${it.posterPath}",
                rating = it.voteAverage ?: 0.0,
                genres = GenreMapper.mapGenreIdsToNames(it.genreIds)
            )
        }
    }

    fun mapDomainToUi(input: List<Movie>): List<com.krisna.diva.movielist.ui.model.Movie> {
        return input.map {
            com.krisna.diva.movielist.ui.model.Movie(
                title = it.title,
                image = it.image,
                rating = it.rating,
                genres = it.genres
            )
        }
    }
}