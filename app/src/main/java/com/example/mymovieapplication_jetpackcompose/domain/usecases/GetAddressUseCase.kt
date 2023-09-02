package com.example.mymovieapplication_jetpackcompose.domain.usecases

import com.example.mymovieapplication_jetpackcompose.domain.repositories.MovieRepositories
import javax.inject.Inject

class GetAddressUseCase @Inject constructor(private val movieRepositories: MovieRepositories) {
    operator fun invoke(url: String) = movieRepositories.getAddress(url)
}