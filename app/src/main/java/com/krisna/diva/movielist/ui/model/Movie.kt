package com.krisna.diva.movielist.ui.model

data class Movie(
    val title: String,
    val image: String,
    val rating: Double,
    val genres: List<String>,
    val overview: String
)