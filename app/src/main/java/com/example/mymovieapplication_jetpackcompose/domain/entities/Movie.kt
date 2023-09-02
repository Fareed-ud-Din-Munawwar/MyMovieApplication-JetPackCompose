package com.example.mymovieapplication_jetpackcompose.domain.entities

data class Movie(
    val title: String = "",
    val release_date: String = "",
    val overview: String = "",
    val original_language: String = "",
    val poster_path: String = "",
    val vote_average: Float = 0F,
    val tag: String = MovieType.DEFAULT.toString(),
    var favourite: Boolean = false,
)
