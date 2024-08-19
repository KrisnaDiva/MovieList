package com.krisna.diva.movielist.utils

import com.krisna.diva.movielist.data.remote.response.ResultsItem
import com.krisna.diva.movielist.model.Movie

object DataMapper {
    fun mapResultsItemToMovie(input: List<ResultsItem?>): List<Movie> {
        return input.mapNotNull { item ->
            item?.let {
                Movie(
                    image = "https://image.tmdb.org/t/p/w500${it.posterPath ?: ""}",
                    title = it.title ?: "",
                    rating = it.voteAverage ?: 0.0,
                    genres = GenreMapper.mapGenreIdsToNames(it.genreIds?.filterNotNull() ?: emptyList())
                )
            }
        }
    }
}