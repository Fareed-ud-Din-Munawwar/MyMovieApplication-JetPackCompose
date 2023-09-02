package com.example.mymovieapplication_jetpackcompose.domain.usecases

import com.example.mymovieapplication_jetpackcompose.domain.repositories.MovieRepositories
import javax.inject.Inject

class FetchMoviesListUseCase @Inject constructor(private val movieRepositories: MovieRepositories) {
    suspend operator fun invoke() = movieRepositories.fetchMoviesList()
}