package com.krisna.diva.movielist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieItemDto(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int>? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("overview")
    val overview: String? = null
)