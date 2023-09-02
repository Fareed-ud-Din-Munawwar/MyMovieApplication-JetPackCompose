package com.example.mymovieapplication_jetpackcompose.domain.repositories

import com.example.mymovieapplication_jetpackcompose.data.localdatabase.MovieEntity
import com.example.mymovieapplication_jetpackcompose.data.repositories.Resource
import com.example.mymovieapplication_jetpackcompose.domain.entities.Movie
import com.example.mymovieapplication_jetpackcompose.domain.entities.MovieResponse
import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface MovieRepositories {
    fun getFavouriteMovieData(): Flow<List<MovieEntity>>

    suspend fun addFavouriteMovie(movie: MovieEntity)

    suspend fun removeFavouriteMovie(movie: MovieEntity)

    suspend fun deleteMovie(movie: MovieEntity)

    fun getMovieData(): Flow<List<MovieEntity>>

    suspend fun setMovieDataBase(list: List<MovieEntity>?)

    suspend fun searchMovieById(id: Long): Flow<MovieEntity>

    suspend fun addNewMovie(customMovie: MovieEntity)

    suspend fun fetchMovieDetails(id: Long): Resource<Movie?>

    suspend fun fetchMoviesList(): Resource<MovieResponse?>

    fun getAddress(url: String): String

    fun getPosterAddress(): String

    fun saveTheme(darkThemeEnabled: Boolean)

    fun getTheme(): Boolean
}