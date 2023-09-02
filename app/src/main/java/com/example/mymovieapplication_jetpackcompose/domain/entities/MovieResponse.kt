package com.example.mymovieapplication_jetpackcompose.domain.entities

data class MovieResponse (
    var page: String,
    var results: List<Movie>,
)