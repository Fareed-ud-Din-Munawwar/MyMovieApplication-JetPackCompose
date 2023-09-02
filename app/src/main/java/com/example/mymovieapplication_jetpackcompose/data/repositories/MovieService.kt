package com.example.mymovieapplication_jetpackcompose.data.repositories

import com.example.mymovieapplication_jetpackcompose.domain.entities.Movie
import com.example.mymovieapplication_jetpackcompose.domain.entities.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET(RemoteDataSource.MOVIES_LIST_API)
    suspend fun getList(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: String,
    ): Response<MovieResponse>

    @GET(RemoteDataSource.MOVIE_DETAILS_API)
    suspend fun getMovie(
        @Path("movie_id", encoded = false) id: Long,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
    ): Response<Movie>
}