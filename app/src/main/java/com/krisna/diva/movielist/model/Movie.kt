package com.krisna.diva.movielist.model

data class Movie(
    val image: String,
    val title: String,
    val rating: Double,
    val genres: List<String>,
)

