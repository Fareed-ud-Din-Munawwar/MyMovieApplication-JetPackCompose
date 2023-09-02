package com.example.mymovieapplication_jetpackcompose.data.localdatabase

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(vararg movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("SELECT * FROM MovieEntity WHERE id = :ID ORDER BY release_date DESC")
    fun getMovieById(ID: Long): Flow<MovieEntity>

    @Query("SELECT * FROM MovieEntity WHERE title LIKE :title ORDER BY release_date DESC")
    fun getMovieByTitle(title: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity ORDER BY release_date DESC")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE favourite = 1 ORDER BY release_date DESC")
    fun getFavouriteMovie(): Flow<List<MovieEntity>>

    @Query("UPDATE MovieEntity SET favourite = 1 WHERE id = :id ")
    suspend fun setFavourite(id: Int)

    @Query("UPDATE MovieEntity SET favourite = 0 WHERE id = :id")
    suspend fun removeFavourite(id: Int)

}