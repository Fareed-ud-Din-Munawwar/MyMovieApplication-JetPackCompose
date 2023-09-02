package com.example.mymovieapplication_jetpackcompose.domain.usecases

import com.example.mymovieapplication_jetpackcompose.data.localdatabase.MovieEntity
import com.example.mymovieapplication_jetpackcompose.domain.entities.Movie
import com.example.mymovieapplication_jetpackcompose.domain.repositories.MovieRepositories
import javax.inject.Inject

class SetMovieDataBaseUseCase @Inject constructor(private val movieRepositories: MovieRepositories) {
    suspend operator fun invoke(list: List<MovieEntity>?) = movieRepositories.setMovieDataBase(list)
}