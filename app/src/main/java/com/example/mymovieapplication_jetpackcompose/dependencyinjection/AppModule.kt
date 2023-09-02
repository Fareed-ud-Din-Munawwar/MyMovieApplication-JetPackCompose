package com.example.assignmentoptimized.dependencyinjection

import android.content.Context
import android.content.SharedPreferences
import com.example.mymovieapplication_jetpackcompose.data.repositories.LocalDataSource
import com.example.mymovieapplication_jetpackcompose.data.repositories.MovieRepositoryImpl
import com.example.mymovieapplication_jetpackcompose.data.repositories.MovieService
import com.example.mymovieapplication_jetpackcompose.data.repositories.RemoteDataSource
import com.example.mymovieapplication_jetpackcompose.domain.repositories.MovieRepositories
import com.example.mymovieapplication_jetpackcompose.domain.usecases.AddFavouriteMovieUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.processNextEventInCurrentThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getRetrofitConfigurer(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun getServices(
        retrofit: Retrofit
    ): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        sharedPreferences: SharedPreferences
    ): MovieRepositories = MovieRepositoryImpl(remoteDataSource, localDataSource, sharedPreferences)

    /*@Provides
    fun provideMovieRepositories(): MovieRepositories =*/

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("preferences_name", Context.MODE_PRIVATE)
    }

}