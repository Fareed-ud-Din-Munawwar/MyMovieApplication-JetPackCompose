package com.example.mymovieapplication_jetpackcompose.data.repositories

import com.example.mymovieapplication_jetpackcompose.data.localdatabase.LocalDBHandler
import com.example.mymovieapplication_jetpackcompose.data.localdatabase.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
private val localDBHandler: LocalDBHandler
) {

    fun getFavouriteMovieData(): Flow<List<MovieEntity>> {
        return localDBHandler.getFavouriteMovie()
    }

    suspend fun addFavouriteMovie(movie: MovieEntity) {
        localDBHandler.setFavourite(movie.id)
    }

    suspend fun removeFavouriteMovie(movie: MovieEntity) {
        localDBHandler.removeFavourite(movie.id)
    }

    suspend fun deleteMovie(movie: MovieEntity) {
        localDBHandler.deleteMovie(movie)
    }

    fun getMovieData(): Flow<List<MovieEntity>> {
        return localDBHandler.getAllMovie()

    }

    suspend fun setDB(list: List<MovieEntity>?) {
        localDBHandler.insertMovie(*list?.toTypedArray() ?: emptyArray())
    }

    suspend fun searchMovieById(id: Long): Flow<MovieEntity> {
        return localDBHandler.getMovieById(id)
    }

    suspend fun addNewMovie(customMovie: MovieEntity) {
        localDBHandler.insertMovie(customMovie)
    }
}