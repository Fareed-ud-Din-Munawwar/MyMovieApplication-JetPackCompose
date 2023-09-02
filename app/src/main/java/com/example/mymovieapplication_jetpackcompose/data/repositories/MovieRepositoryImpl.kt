package com.example.mymovieapplication_jetpackcompose.data.repositories

import android.content.SharedPreferences
import com.example.mymovieapplication_jetpackcompose.data.localdatabase.MovieEntity
import com.example.mymovieapplication_jetpackcompose.domain.entities.Movie
import com.example.mymovieapplication_jetpackcompose.domain.entities.MovieResponse
import com.example.mymovieapplication_jetpackcompose.domain.repositories.MovieRepositories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class MovieRepositoryImpl  @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val sharedPreferences: SharedPreferences
): MovieRepositories {
    override fun getFavouriteMovieData(): Flow<List<MovieEntity>> = localDataSource.getFavouriteMovieData()

    override suspend fun addFavouriteMovie(movie: MovieEntity) = localDataSource.addFavouriteMovie(movie)

    override suspend fun removeFavouriteMovie(movie: MovieEntity) = localDataSource.removeFavouriteMovie(movie)

    override suspend fun deleteMovie(movie: MovieEntity) = localDataSource.deleteMovie(movie)

    override fun getMovieData(): Flow<List<MovieEntity>> = localDataSource.getMovieData()

    override suspend fun setMovieDataBase(list: List<MovieEntity>?) = localDataSource.setDB(list)

    override suspend fun searchMovieById(id: Long): Flow<MovieEntity> = localDataSource.searchMovieById(id)

    override suspend fun addNewMovie(customMovie: MovieEntity) = localDataSource.addNewMovie(customMovie)

    override suspend fun fetchMovieDetails(id: Long): Resource<Movie?>
    {
        val response = remoteDataSource.sendDetailGetRequest(id)
        return if (response.status.isSuccess) {
            Resource.success(response.data)
        } else {
            Resource.error(response.message ?: "Unknown Network Error")
        }    }

    override suspend fun fetchMoviesList(): Resource<MovieResponse?>
    {
        val response = remoteDataSource.sendListGetRequest()
        return if (response.status.isSuccess) {
            setMovieDataBase(response.data?.results?.map { MovieEntity(it.title.hashCode(),it) } ?: emptyList())
            Resource.success(response.data)
        } else {
            Resource.error(response.message ?: "Unknown Network Error")
        }
    }

    override fun getAddress(url:String):String = remoteDataSource.getAddress(url)

    override fun getPosterAddress():String = remoteDataSource.getPosterAddress()

    override fun saveTheme(darkThemeEnabled : Boolean) {
        sharedPreferences.edit().putBoolean(DARK_THEME_ENABLED,darkThemeEnabled).apply()
    }

    override fun getTheme(): Boolean {
        return sharedPreferences.getBoolean(DARK_THEME_ENABLED,false)
    }

    companion object {
        const val DARK_THEME_ENABLED = "DARK_THEME_ENABLED"
    }
}