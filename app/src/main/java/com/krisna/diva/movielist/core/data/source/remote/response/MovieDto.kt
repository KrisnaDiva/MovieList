package com.krisna.diva.movielist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDto(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<MovieItemDto?>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)


