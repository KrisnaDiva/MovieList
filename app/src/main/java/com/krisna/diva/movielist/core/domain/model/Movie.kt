package com.krisna.diva.movielist.core.domain.model

data class Movie(
    val image: String,
    val title: String,
    val rating: Double,
    val genres: List<String>,
)

