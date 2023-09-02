package com.example.mymovieapplication_jetpackcompose.domain.usecases

import com.example.mymovieapplication_jetpackcompose.domain.repositories.MovieRepositories
import javax.inject.Inject

class FetchMovieDetailsUseCase @Inject constructor(private val movieRepositories: MovieRepositories) {
    suspend operator fun invoke(id: Long) = movieRepositories.fetchMovieDetails(id)
}