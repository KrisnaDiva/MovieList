package com.krisna.diva.movielist.core.utils

import com.krisna.diva.movielist.core.data.source.remote.response.MovieItemDto
import com.krisna.diva.movielist.core.domain.model.Movie as DomainMovie

object DataMapper {
    fun mapResponsesToDomain(input: List<MovieItemDto>): List<DomainMovie> {
        return input.map {
            DomainMovie(
                title = it.title ?: "",
                image = "https://image.tmdb.org/t/p/w500${it.posterPath}",
                rating = it.voteAverage ?: 0.0,
                genres = GenreMapper.mapGenreIdsToNames(it.genreIds),
                overview = it.overview ?: ""
            )
        }
    }
}