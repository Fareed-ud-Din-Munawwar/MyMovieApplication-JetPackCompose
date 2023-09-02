package com.example.mymovieapplication_jetpackcompose.domain.usecases

import com.example.mymovieapplication_jetpackcompose.domain.repositories.MovieRepositories
import javax.inject.Inject

class GetThemeStatusUseCase @Inject constructor(private val movieRepositories: MovieRepositories) {
    operator fun invoke() = movieRepositories.getTheme()
}