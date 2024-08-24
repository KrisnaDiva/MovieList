package com.krisna.diva.movielist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieItemDto(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("genre_ids")
    val genres: List<Int>? = null,

    @field:SerializedName("poster_path")
    val image: String? = null,

    @field:SerializedName("vote_average")
    val rating: Double? = null,

    @field:SerializedName("overview")
    val overview: String? = null
)