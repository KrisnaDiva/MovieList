package com.krisna.diva.movielist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDto(

    @field:SerializedName("results")
    val results: List<MovieItemDto?>? = null,
)


