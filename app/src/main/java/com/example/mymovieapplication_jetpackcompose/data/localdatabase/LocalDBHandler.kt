package com.example.mymovieapplication_jetpackcompose.data.localdatabase

import android.content.Context
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDBHandler @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val db = Room.databaseBuilder(context, LocalDatabase::class.java, "Database").build()
    //private val db =  Room.databaseBuilder(context, LocalDatabase::class.java, "Database").allowMainThreadQueries().build()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(vararg movie: MovieEntity) {
        db.movieDao().insertMovie(*movie)
    }

    suspend fun updateMovie(movie: MovieEntity) {
        db.movieDao().updateMovie(movie)
    }

    suspend fun deleteMovie(movie: MovieEntity) {
        db.movieDao().deleteMovie(movie)
    }

    suspend fun getMovieById(id: Long): Flow<MovieEntity> {
        return db.movieDao().getMovieById(id)
    }

    suspend fun getMovieByTitle(title: String): Flow<List<MovieEntity>> {
        return db.movieDao().getMovieByTitle(title)

    }

    fun getAllMovie(): Flow<List<MovieEntity>> {
        return db.movieDao().getAllMovie()
    }

    fun getFavouriteMovie(): Flow<List<MovieEntity>> {
        return db.movieDao().getFavouriteMovie()
    }

    suspend fun setFavourite(id: Int) {
        return db.movieDao().setFavourite(id)
    }

    suspend fun removeFavourite(id: Int) {
        return db.movieDao().removeFavourite(id)
    }
}