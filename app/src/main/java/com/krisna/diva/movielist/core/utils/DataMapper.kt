package com.krisna.diva.movielist.core.utils

import com.krisna.diva.movielist.core.data.source.remote.response.MovieItem
import com.krisna.diva.movielist.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToDomain(input: List<MovieItem>): List<Movie> {
        return input.map {
            Movie(
                title = it.title ?: "",
                image = "https://image.tmdb.org/t/p/w500${it.posterPath}",
                rating = it.voteAverage ?: 0.0,
                genres = GenreMapper.mapGenreIdsToNames(it.genreIds)
            )
        }
    }
}